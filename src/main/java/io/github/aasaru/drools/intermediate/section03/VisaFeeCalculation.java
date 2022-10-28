/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.section03;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.visa.VisaApplication;
import io.github.aasaru.drools.intermediate.domain.visa.VisaApplicationFolder;
import io.github.aasaru.drools.intermediate.domain.visa.VisaFee;
import io.github.aasaru.drools.intermediate.repository.ApplicationRepository;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.SequentialOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class VisaFeeCalculation {

    public static void main(final String[] args) {
        int step = Common.promptForStep(3, args, 1, 13);

        boolean singleDroolsSession = true;
        if (step == 11) {

            if (Common.promptForYesNoQuestion("Do you want run a separate Drools session for each visa application ?")) {
                singleDroolsSession = false;
            }

        }
        else if (step == 12 || step == 13) {
            executeAsStatelessSessionInDroolsSequentialMode(step);
            return;
        }
        else if (step == 14) {
            executeFromTemplate(step);
            return;
        }
        execute(step, singleDroolsSession);
    }

    static Collection<VisaFee> execute(int step, boolean singleDroolsSession) {
        System.out.println("Running step " + step);

        List<Object> factsToInsert = new ArrayList<>();

        if (step <= 5) {
            factsToInsert.addAll(ApplicationRepository.getFamilyVisaApplications());
        }

        if (step >= 5) {
            factsToInsert.addAll(ApplicationRepository.getPassports());
        }

        Collection<VisaFee> visaFees = new ArrayList<>();

        if (singleDroolsSession) {

            if (step >= 6) {
                factsToInsert.addAll(ApplicationRepository.getVisaApplications());
            }

            visaFees.addAll( executeDroolsSession(step, factsToInsert, 1) );
        }
        else {
            int sessionNo = 1;
            for (VisaApplication visaApplication : ApplicationRepository.getVisaApplications()) {

                List<Object> factsList = new ArrayList<>(factsToInsert);
                factsList.add(visaApplication);

                visaFees.addAll( executeDroolsSession(step, factsList, sessionNo++) );
            }
        }

        return visaFees;
    }

    static List<VisaApplicationFolder> executeFromTemplate(int step) {


        List<VisaApplicationFolder> visaApplicationFolders = ApplicationRepository.getVisaApplicationFolders();

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "VisaFeeCalculationStep14" );



        System.out.println("==== EXECUTING FOLLOWING FACTS AS DROOLS STATELESS SESSION ==== ");

        visaApplicationFolders.forEach(ksession::insert);
        visaApplicationFolders.forEach(System.out::println);



        System.out.println("==== DROOLS STATELESS SESSION START ==== ");
        ksession.fireAllRules();
        System.out.println("==== DROOLS STATELESS SESSION END ==== ");

        visaApplicationFolders.forEach(System.out::println);

        return visaApplicationFolders;
    }

    static List<VisaApplicationFolder> executeAsStatelessSessionInDroolsSequentialMode(int step) {
        List<VisaApplicationFolder> visaApplicationFolders = ApplicationRepository.getVisaApplicationFolders();

        KieServices ks = KieServices.Factory.get();
        KieBaseConfiguration kieBaseConf = ks.newKieBaseConfiguration();
        kieBaseConf.setOption(SequentialOption.YES);
        KieBase kieBase = ks.getKieClasspathContainer().getKieBase("section03step" + step);

        StatelessKieSession ksession = kieBase.newStatelessKieSession();


        System.out.println("==== EXECUTING FOLLOWING FACTS AS DROOLS STATELESS SESSION ==== ");

        visaApplicationFolders.forEach(System.out::println);

        System.out.println("==== DROOLS STATELESS SESSION START ==== ");
        ksession.execute(visaApplicationFolders);
        System.out.println("==== DROOLS STATELESS SESSION END ==== ");

        visaApplicationFolders.forEach(System.out::println);


        return visaApplicationFolders;
    }


    static Collection<VisaFee> executeDroolsSession(int step, Collection<Object> factsToInsert, int sessionNo) {
        KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("VisaFeeCalculationStep" + step);

        System.out.println("==== ADDING TO DROOLS SESSION #"+sessionNo+" ==== ");
        factsToInsert.forEach(ksession::insert); // TODO execute
        factsToInsert.forEach(System.out::println);

        System.out.println("==== DROOLS SESSION #"+sessionNo+" START ==== ");
        ksession.fireAllRules();

        System.out.println("==== DROOLS SESSION #"+sessionNo+" END ==== ");

        Collection<VisaFee> fees = getVisaFeesFromKieSession(ksession);

        System.out.println("==== FEES FROM SESSION #"+sessionNo+" END ==== ");
        fees.forEach(System.out::println);
        System.out.println();

        if (Common.disposeSession) {
            ksession.dispose();
        }

        return fees;
    }

    private static Collection<VisaFee> getVisaFeesFromKieSession(KieSession ksession) {
        Collection<VisaFee> fees = ksession.getObjects(o -> o.getClass() == VisaFee.class)
             .stream()
             .map(fee -> (VisaFee) fee)
             .collect(Collectors.toList());
        return fees;
    }

}

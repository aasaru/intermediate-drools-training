package io.github.aasaru.drools.intermediate.section04;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.visa.VisaApplicationFolder;
import io.github.aasaru.drools.intermediate.domain.visa.VisaVerdict;
import io.github.aasaru.drools.intermediate.repository.ApplicationRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class DetermineVisaVerdict {

    public static void main(final String[] args) {
        int step = Common.promptForStep(4, args, 1, 14);

        executeFromTemplate(step);

    }

    static void executeFromTemplate(int step) {


        List<VisaApplicationFolder> visaApplicationFolders = ApplicationRepository.getVisaApplicationFoldersWithBookings();

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "VisaVerdictSection04Step" + step );



        System.out.println("==== EXECUTING FOLLOWING FACTS AS DROOLS STATELESS SESSION ==== ");

        visaApplicationFolders.forEach(ksession::insert);
        visaApplicationFolders.forEach(System.out::println);



        System.out.println("==== DROOLS STATELESS SESSION START ==== ");
        ksession.fireAllRules();
        System.out.println("==== DROOLS STATELESS SESSION END ==== ");

        Collection<VisaVerdict> verdicts = getVisaVerdictsFromKieSession(ksession);


        System.out.println("==== VERDICTS FROM SESSION END ==== ");
        verdicts.forEach(System.out::println);
        System.out.println();
    }

    private static Collection<VisaVerdict> getVisaVerdictsFromKieSession(KieSession ksession) {
        Collection<VisaVerdict> fees = ksession.getObjects(o -> o.getClass() == VisaVerdict.class)
                .stream()
                .map(fee -> (VisaVerdict) fee)
                .collect(Collectors.toList());
        return fees;
    }

}

package io.github.aasaru.drools.intermediate.section04;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.visa.VisaApplicationFolder;
import io.github.aasaru.drools.intermediate.repository.ApplicationRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;


public class VisaFeeCalculation {

    public static void main(final String[] args) {
        int step = Common.promptForStep(4, args, 1, 14);

        executeFromTemplate(step);

    }

    static List<VisaApplicationFolder> executeFromTemplate(int step) {


        List<VisaApplicationFolder> visaApplicationFolders = ApplicationRepository.getVisaApplicationFolders();

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "VisaFeeCalculationSection04Step" + step );



        System.out.println("==== EXECUTING FOLLOWING FACTS AS DROOLS STATELESS SESSION ==== ");

        visaApplicationFolders.forEach(ksession::insert);
        visaApplicationFolders.forEach(System.out::println);



        System.out.println("==== DROOLS STATELESS SESSION START ==== ");
        ksession.fireAllRules();
        System.out.println("==== DROOLS STATELESS SESSION END ==== ");

        visaApplicationFolders.forEach(System.out::println);

        return visaApplicationFolders;
    }

}

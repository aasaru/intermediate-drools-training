package io.github.aasaru.drools.intermediate.section03;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.visa.VisaFee;
import io.github.aasaru.drools.intermediate.repository.ApplicationRepository;
import org.drools.compiler.compiler.DecisionTableFactory;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.core.io.impl.FileSystemResource;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.DecisionTableConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class VisaFeeCalculation {

    public static void main(final String[] args) {

        int step = Common.promptForStep(3, args, 1, 12);

        // TODO 9 = 7 (except running mode)

        if (true) {


            // TODO - remove absolute path and make work when running from JAR

            DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();

            FileSystemResource
                 spreadsheetFile =
                 new FileSystemResource("/Users/juhan/spaces/github/intermediate-drools-training/src/main/resources/io/github/aasaru/drools/intermediate/section03/step"+step+"/step"+step+".xls");
            String generatedDrl = DecisionTableFactory.loadFromResource(spreadsheetFile, decisionTableConfiguration);

            System.out.println(generatedDrl);

        }

        execute(step);


    }

    static Collection<VisaFee> execute(int step) {
        System.out.println("Running step " + step);


        List<Object> factsToInsert = new ArrayList<>();

        factsToInsert.addAll(ApplicationRepository.getGroupVisaApplications());
        factsToInsert.addAll(ApplicationRepository.getVisaApplications());
        factsToInsert.addAll(ApplicationRepository.getPassports());


        return executeInSingleSession(step, factsToInsert);

    }

    static Collection<VisaFee> executeInSingleSession(int step, Collection<Object> factsToInsert) {

        KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("VisaFeeCalculationStep" + step);

        factsToInsert.forEach(ksession::insert);
        factsToInsert.forEach(System.out::println);

        System.out.println("==== DROOLS SESSION START ==== ");
        ksession.fireAllRules();

        System.out.println("==== DROOLS SESSION END ==== ");

        Collection<VisaFee> fees = getVisaFeesFromKieSession(ksession);

        System.out.println("== Fees from session == ");
        fees.forEach(System.out::println);

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

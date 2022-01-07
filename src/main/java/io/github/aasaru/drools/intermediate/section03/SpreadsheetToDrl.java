package io.github.aasaru.drools.intermediate.section03;

import io.github.aasaru.drools.intermediate.Common;
import org.drools.compiler.compiler.DecisionTableFactory;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.core.io.impl.FileSystemResource;
import org.kie.internal.builder.DecisionTableConfiguration;

public class SpreadsheetToDrl {

    public static void main(String[] args) {

        int step = Common.promptForStep(3, new String[]{"RULES"}, 1, 12);


        // TODO - remove absolute path and make work when running from JAR

        DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();

        FileSystemResource
                spreadsheetFile =
                new FileSystemResource("/Users/juhan/spaces/github/intermediate-drools-training/src/main/resources/io/github/aasaru/drools/intermediate/section03/step" + step + "/step" + step + ".xls");
        String generatedDrl = DecisionTableFactory.loadFromResource(spreadsheetFile, decisionTableConfiguration);

        System.out.println();
        System.out.println(" ######################## RULES ######################## ");
        System.out.println();
        System.out.println(generatedDrl);
        System.out.println();
        System.out.println(" ######################## END ######################## ");
        System.out.println();


    }

}

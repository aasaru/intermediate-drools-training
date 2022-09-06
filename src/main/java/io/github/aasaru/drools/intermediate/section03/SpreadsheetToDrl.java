package io.github.aasaru.drools.intermediate.section03;

import io.github.aasaru.drools.intermediate.Common;
import org.drools.compiler.compiler.DecisionTableFactory;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.core.io.impl.FileSystemResource;
import org.kie.internal.builder.DecisionTableConfiguration;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class SpreadsheetToDrl {

    public static void main(String[] args) {

        int step = Common.promptForStep(3, new String[]{"RULES"}, 1, 12);

        DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();

        FileSystemResource
                spreadsheetFile =
                new FileSystemResource(getFile("io/github/aasaru/drools/intermediate/section03/step" + step + "/step" + step + ".xls"));

        String generatedDrl = DecisionTableFactory.loadFromResource(spreadsheetFile, decisionTableConfiguration);

        System.out.println();
        System.out.println(" ######################## RULES ######################## ");
        System.out.println();
        System.out.println(generatedDrl);
        System.out.println();
        System.out.println(" ######################## END ######################## ");
        System.out.println();
    }

    private static File getFile(String fileName) {
        ClassLoader classLoader = SpreadsheetToDrl.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(Objects.requireNonNull(resource).getFile());
    }

}

package io.github.aasaru.drools.intermediate.section04;

import io.github.aasaru.drools.intermediate.Common;
import org.drools.compiler.compiler.DecisionTableFactory;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.core.io.impl.FileSystemResource;
import org.kie.internal.builder.DecisionTableConfiguration;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class FilledTemplateToDrl {

    public static void main(String[] args) {

        int step = Common.promptForStep(4, new String[]{"RULES"}, 1, 3);

        DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();

        String generatedDrl = "";


        FileSystemResource templateFile =
                new FileSystemResource(getFile("io/github/aasaru/drools/intermediate/section04/step" + step + "/ruleTemplates.drt"));

        decisionTableConfiguration.addRuleTemplateConfiguration(templateFile, 2, 2);

        FileSystemResource
                dataFile =
                new FileSystemResource(getFile("io/github/aasaru/drools/intermediate/section04/step" + step + "/data.xls"));

        List<String> individualRules = DecisionTableFactory.loadFromInputStreamWithTemplates(dataFile, decisionTableConfiguration);
        generatedDrl = String.join("\\n", individualRules);

        System.out.println();
        System.out.println(" ######################## RULES FROM TEMPLATE ######################## ");

        System.out.println();
        System.out.println(generatedDrl);
        System.out.println();
        System.out.println(" ######################## END ######################## ");
        System.out.println();
    }

    private static File getFile(String fileName) {
        ClassLoader classLoader = FilledTemplateToDrl.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(Objects.requireNonNull(resource).getFile());
    }

}

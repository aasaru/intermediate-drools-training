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

public class GetDrl {

    public static void main(String[] args) {

        int step = Common.promptForStep(4, new String[]{"RULES"}, 1, 4);

        String generatedDrl = "";

        if (step == 1) {
            generatedDrl = getStep1Drl();
        }
        else if (step == 2) {
            generatedDrl = DetermineVisaVerdict.getStep2Drl(DetermineVisaVerdict.getDataInTabulatedFormat());
        }
        else if (step == 3) {
            generatedDrl = DetermineVisaVerdict.getStep3AsDrl(DetermineVisaVerdict.getDataAsModelObjectsForFreemarker());
        }

        System.out.println();
        System.out.println(" ######################## ACTUAL RULES ######################## ");

        System.out.println();
        System.out.println(generatedDrl);
        System.out.println();
        System.out.println(" ######################## END ######################## ");
        System.out.println();
    }

    private static String getStep1Drl() {
        DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();
        String generatedDrl;
        FileSystemResource templateFile =
                new FileSystemResource(getFile("io/github/aasaru/drools/intermediate/section04/step1/ruleTemplates.drt"));

        decisionTableConfiguration.addRuleTemplateConfiguration(templateFile, 2, 2);

        FileSystemResource
                dataFile =
                new FileSystemResource(getFile("io/github/aasaru/drools/intermediate/section04/step1/data.xls"));

        List<String> individualRules = DecisionTableFactory.loadFromInputStreamWithTemplates(dataFile, decisionTableConfiguration);
        generatedDrl = String.join("\\n", individualRules);
        return generatedDrl;
    }

    private static File getFile(String fileName) {
        ClassLoader classLoader = GetDrl.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(Objects.requireNonNull(resource).getFile());
    }

}

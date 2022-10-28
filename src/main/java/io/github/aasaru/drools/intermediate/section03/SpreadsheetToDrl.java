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
import org.drools.compiler.compiler.DecisionTableFactory;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.core.io.impl.FileSystemResource;
import org.kie.internal.builder.DecisionTableConfiguration;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class SpreadsheetToDrl {

    public static void main(String[] args) {

        int step = Common.promptForStep(3, new String[]{"RULES"}, 1, 13);

        DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();

        String generatedDrl = "";

        if (step > 0 && step < 13) {
            FileSystemResource
                    spreadsheetFile =
                    new FileSystemResource(getFile("io/github/aasaru/drools/intermediate/section03/step" + step + "/step" + step + ".xls"));

            generatedDrl = DecisionTableFactory.loadFromResource(spreadsheetFile, decisionTableConfiguration);

            System.out.println();
            System.out.println(" ######################## RULES FROM SPREADSHEET ######################## ");
        }
        else if (step == 14) {
            FileSystemResource templateFile =
                    new FileSystemResource(getFile("io/github/aasaru/drools/intermediate/section03/step" + step + "/step" + step + ".drt"));

            decisionTableConfiguration.addRuleTemplateConfiguration(templateFile, 2, 2);

            FileSystemResource
                    dataFile =
                    new FileSystemResource(getFile("io/github/aasaru/drools/intermediate/section03/step" + step + "/step" + step + ".xls"));

            List<String> individualRules = DecisionTableFactory.loadFromInputStreamWithTemplates(dataFile, decisionTableConfiguration);
            generatedDrl = String.join("\\n", individualRules);

            System.out.println();
            System.out.println(" ######################## RULES FROM TEMPLATE ######################## ");
        }
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

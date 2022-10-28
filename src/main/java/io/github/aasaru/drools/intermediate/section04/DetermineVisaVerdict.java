/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.section04;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.visa.VisaApplicationFolder;
import io.github.aasaru.drools.intermediate.domain.visa.VisaVerdict;
import io.github.aasaru.drools.intermediate.repository.ApplicationRepository;
import io.github.aasaru.drools.intermediate.section04.internal.FromListDataProvider;
import io.github.aasaru.drools.intermediate.section04.internal.VerdictRuleData;
import org.drools.template.DataProviderCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class DetermineVisaVerdict {

    public static void main(final String[] args) throws InstantiationException, IllegalAccessException {
        int step = Common.promptForStep(4, args, 1, 6);
        System.out.println("Running step " + step);

        if (step == 1) {
            KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession( "VisaVerdictSection04Step" + step );
            executeInSession(kieSession, step);
        }
        else if (step == 2) {
            final String drl = getStep2Drl(getDataInTabulatedFormat());
            KieContainer kContainer = loadContainerFromString(drl);
            KieSession kieSession = kContainer.newKieSession();

            executeInSession(kieSession, step);
        }
        else if (step == 3) {
            final String drl = getStep3AsDrl(getDataAsModelObjectsForFreemarker());
            KieContainer kContainer = loadContainerFromString(drl);
            KieSession kieSession = kContainer.newKieSession();

            executeInSession(kieSession, step);
        }
        else if (step == 4) {
            KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession( "VisaVerdictSection04Step" + step );
            executeInSession(kieSession, step);
        }
        else if (step == 5 || step == 6) {

            KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession( "VisaVerdictSection04Step" + step );

            Gson gConverter = new GsonBuilder().setPrettyPrinting().create();

            final FactType factTypeDeclaredInDrl = kieContainer
                    .getKieBase("section04step" + step)
                    .getFactType("io.github.aasaru.drools.intermediate.section04.step" + step, "RiskFactor");


            if (step == 6) {
                String factAsJson = "{  'visaApplicationId': 1,      'risk': 'CRIMINAL_RECORD'  }";

                Object factToInsert = factTypeDeclaredInDrl.newInstance();
                Object factObject = gConverter.fromJson(factAsJson, factToInsert.getClass());
                kieSession.insert(factObject);
            }

            executeInSession(kieSession, step);

            List<Object> allRiskFactors = new ArrayList<>(kieSession.getObjects(o -> o.getClass() == factTypeDeclaredInDrl.getFactClass()));
            String risksAsJson = gConverter.toJson(allRiskFactors);
            System.out.println("All risks factors as JSON: " + risksAsJson);

        }
    }

    public static String getStep3AsDrl(List<VerdictRuleData> dataAsModelObjectsForFreemarker) {
        Configuration freemarkerConfiguration = getFreemarkerConfiguration();

        /* Get the template (uses cache internally) */
        Template headerTemplate = getTemplate(freemarkerConfiguration, "step3/freemarkerHeaderTemplate.ftl");
        Template ruleTemplate = getTemplate(freemarkerConfiguration, "step3/freemarkerRuleTemplate.ftl");

        StringWriter out = new StringWriter();

        processTemplate(headerTemplate, null, out);

        for (VerdictRuleData verdictRuleData : dataAsModelObjectsForFreemarker) {
            processTemplate(ruleTemplate, verdictRuleData, out);
        }

        return out.toString();
    }

    private static Configuration getFreemarkerConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        ClassTemplateLoader ctl = new ClassTemplateLoader(DetermineVisaVerdict.class, "");
        cfg.setTemplateLoader(ctl);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        return cfg;
    }

    private static Template getTemplate(Configuration cfg, String templatePath) {
        Template temp = null;
        try {
            temp = cfg.getTemplate(templatePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    private static void processTemplate(Template temp, VerdictRuleData verdictRuleData, StringWriter out) {
        try {
            temp.process(verdictRuleData, out);
            out.write("\n\n");
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStep2Drl(List<String[]> tabulatedData) {

        FromListDataProvider inMemoryDataProvider = new FromListDataProvider(tabulatedData);

        final DataProviderCompiler converter = new DataProviderCompiler();
        return converter.compile( inMemoryDataProvider, getTemplate() );
    }

    public static List<String[]> getDataInTabulatedFormat() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"0",   "15", "",     "",      ""});
        data.add(new String[]{"16",  "59", "true", "false", "0.8"});
        data.add(new String[]{"16",  "59", "true", "true",  "0.3"});
        data.add(new String[]{"16",  "59", "",     "",      ""});
        data.add(new String[]{"60", "200", "",     "",      ""});
        return data;
    }

    public static List<VerdictRuleData> getDataAsModelObjectsForFreemarker() {
        List<VerdictRuleData> data = new ArrayList<>();

        int ruleNumber = 0;

        data.add(VerdictRuleData.newBuilder()
                .withRuleNumber(++ruleNumber)
                .withMinAge(0)
                .withMaxAge(15)
                .withIsUnemployed(null)
                .build());

        data.add(VerdictRuleData.newBuilder()
                .withRuleNumber(++ruleNumber)
                .withMinAge(16)
                .withMaxAge(59)
                .withIsUnemployed(true)
                .withHasBooking(false)
                .withPercentageToInterview(0.8)
                .build());

        data.add(VerdictRuleData.newBuilder()
                .withRuleNumber(++ruleNumber)
                .withMinAge(16)
                .withMaxAge(59)
                .withIsUnemployed(true)
                .withHasBooking(true)
                .withPercentageToInterview(0.3)
                .build());

        data.add(VerdictRuleData.newBuilder()
                .withRuleNumber(++ruleNumber)
                .withMinAge(16)
                .withMaxAge(59)
                .withIsUnemployed(null)
                .build());

        data.add(VerdictRuleData.newBuilder()
                .withRuleNumber(++ruleNumber)
                .withMinAge(60)
                .withMaxAge(200)
                .withIsUnemployed(null)
                .build());

        return data;
    }

    private static InputStream getTemplate() {
        return DetermineVisaVerdict.class.getResourceAsStream( "step2/ruleTemplate.drt" );
    }

    static void executeInSession(KieSession ksession, int step) {
        List<VisaApplicationFolder> visaApplicationFolders = ApplicationRepository.getVisaApplicationFoldersWithBookings();


        System.out.println("==== INSERT FACTS INTO DROOLS SESSION ==== ");
        visaApplicationFolders.forEach(ksession::insert);
        visaApplicationFolders.forEach(System.out::println);

        System.out.println("==== DROOLS SESSION START ==== ");
        ksession.fireAllRules();
        System.out.println("==== DROOLS SESSION END ==== ");

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

    private static KieContainer loadContainerFromString(String rules) {
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        kfs.write("src/main/resources/rules.drl", rules);

        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();

        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
        return kContainer;
    }

}

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
import io.github.aasaru.drools.intermediate.TestUtil;
import io.github.aasaru.drools.intermediate.domain.visa.VisaApplicationFolder;
import io.github.aasaru.drools.intermediate.domain.visa.VisaFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class VisaFeeCalculationTest {

    @BeforeEach
    void before() {
        Common.disposeSession = false;
    }

    @Test
    void testStep1() {
        int step = 1;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step, true);
    }

    @Test
    void testStep2() {
        int step = 2;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step, true);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),
                new VisaFee(22, 140)));
    }

    @Test
    void testStep3() {
        int step = 3;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step, true);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),
                new VisaFee(22, 140)));
    }

    @Test
    void testStep4() {
        int step = 4;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step, true);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),
                new VisaFee(22, 140)));
    }

    @Test
    void testStep5() {
        int step = 5;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step, true);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),
                new VisaFee(22, 140),

                new VisaFee(null, 64),
                new VisaFee(null, 64), // TODO double?
                new VisaFee(null, 105),
                new VisaFee(null, 70)


        ));
    }


    @Test
    void testStep6() {
        int step = 6;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step, true);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(1, 70),
                new VisaFee(2, 64),
                new VisaFee(3, 64),
                new VisaFee(4, 200)
        ));
    }

    @Test
    void testStep7() {
        int step = 7;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step, true);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(1, 70),
                new VisaFee(2, 64),
                new VisaFee(3, 32),
                new VisaFee(3, 64),
                new VisaFee(4, 200)
        ));
    }

    @Test
    void testStep8() {
        int step = 8;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step, true);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(1, 70),
                new VisaFee(2, 64),
                new VisaFee(3, 32),
                new VisaFee(4, 200)
        ));
    }

    @Test
    void testStep9() {
        int step = 9;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        Collection<VisaFee> visaFees = VisaFeeCalculation.execute(step, true);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(1, 70),
                new VisaFee(2, 64),
                new VisaFee(3, 32),
                new VisaFee(4, 200)
        ));
    }

    @Test
    void testStep10() {
        int step = 10;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        Collection<VisaFee> visaFees = VisaFeeCalculation.execute(step, true);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(1, 70),
                new VisaFee(2, 64),
                new VisaFee(3, 32),
                new VisaFee(4, 200)
        ));
    }

    @Test
    void testStep11_singleDroolsSession() {
        int step = 11;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        Collection<VisaFee> visaFees = VisaFeeCalculation.execute(step, true);

        assertThat(visaFees.size(), is(1));
    }

    @Test
    void testStep11_multipleDroolsSessions() {
        int step = 11;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        Collection<VisaFee> visaFees = VisaFeeCalculation.execute(step, false);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(1, 70),
                new VisaFee(2, 64), // country CA, cannot get discount
                new VisaFee(3, 32),
                new VisaFee(4, 200)
        ));
    }

    @Test
    void testStep12() {
        int step = 12;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        List<VisaApplicationFolder> visaApplicationFolders = VisaFeeCalculation.executeAsStatelessSessionInDroolsSequentialMode(step);


        Map<Integer, Integer> feeOfVisaApplication = visaApplicationFolders.stream().collect(Collectors.toMap(
                VisaApplicationFolder::getVisaApplicationId, VisaApplicationFolder::getVisaFee
        ));

        assertThat(feeOfVisaApplication.get(1), is(70));
        assertThat(feeOfVisaApplication.get(2), is(64));
        assertThat(feeOfVisaApplication.get(3), is(oneOf(32, 64)));
        assertThat(feeOfVisaApplication.get(4), is(200));
    }


    @Test
    void testStep13() {
        int step = 13;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        List<VisaApplicationFolder> visaApplicationFolders = VisaFeeCalculation.executeAsStatelessSessionInDroolsSequentialMode(step);


        Map<Integer, Integer> feeOfVisaApplication = visaApplicationFolders.stream().collect(Collectors.toMap(
                VisaApplicationFolder::getVisaApplicationId, VisaApplicationFolder::getVisaFee
        ));

        assertThat( feeOfVisaApplication.get(1), is(70) );
        assertThat( feeOfVisaApplication.get(2), is(64) );
        assertThat( feeOfVisaApplication.get(3), is(32) );
        assertThat( feeOfVisaApplication.get(4), is(200) );
    }

}
package io.github.aasaru.drools.intermediate.section04;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.TestUtil;
import io.github.aasaru.drools.intermediate.domain.VisaFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

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

        VisaFeeCalculation.execute(step);
    }

    @Test
    void testStep2() {
        int step = 2;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100)));
    }

    @Test
    void testStep3() {
        int step = 3;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100)));
    }

    @Test
    void testStep4() {
        int step = 4;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100)));
    }

    @Test
    void testStep5() {
        int step = 5;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100)));
    }


    @Test
    void testStep6() {
        int step = 6;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),

                new VisaFee(1, 106),
                new VisaFee(2, 64),
                new VisaFee(3, 64),
                new VisaFee(4, 105)
        ));
    }

    @Test
    void testStep7() {
        int step = 7;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),

                new VisaFee(1, 106),
                new VisaFee(2, 64),
                new VisaFee(3, 64), // TODO TWICE!!!
                new VisaFee(3, 32),
                new VisaFee(4, 105)
        ));
    }

    @Test
    void testStep8() {
        int step = 8;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        VisaFeeCalculation.execute(step);

        KieSession ksession = TestUtil.getKieSession("VisaFeeCalculationStep", step);

        List<VisaFee> visaFees = new ArrayList<>();

        TestUtil.addObjectsOfType(ksession, visaFees, VisaFee.class);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),

                new VisaFee(1, 106),
                new VisaFee(2, 64),
                new VisaFee(3, 32), // TODO TWICE!!!
                new VisaFee(4, 105)
        ));
    }

    @Test
    void testStep9() {
        int step = 9;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        Collection<VisaFee> visaFees = VisaFeeCalculation.execute(step);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),

                new VisaFee(1, 200), // TODO was 106
                new VisaFee(2, 64),
                new VisaFee(3, 32),
                new VisaFee(3, 64), // note that we want to demonstrate duplicate
                new VisaFee(4, 200) // TODO was 105
        ));
    }

    @Test
    void testStep10() {
        int step = 10;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        Collection<VisaFee> visaFees = VisaFeeCalculation.execute(step);

        assertThat(visaFees, containsInAnyOrder(
                new VisaFee(20, 300),
                new VisaFee(21, 100),

                new VisaFee(1, 200), // TODO was 106
                new VisaFee(2, 64),
                new VisaFee(3, 32),
                new VisaFee(4, 200) // TODO was 105
        ));
    }

    @Test
    void testStep11() {
        int step = 11;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        Collection<VisaFee> visaFees = VisaFeeCalculation.execute(step);

        assertThat(visaFees, containsInAnyOrder(
               // new VisaFee(20, 300), TODO where did they go?
               // new VisaFee(21, 100),

                new VisaFee(1, 106),
                new VisaFee(1, 50), // TODO duplicate!
                new VisaFee(2, 64),
                new VisaFee(3, 32),
                new VisaFee(4, 60),
                new VisaFee(4, 105) // TODO duplicate
        ));
    }


    @Test
    void testStep12() {
        int step = 12;

        String kieSessionName = "VisaFeeCalculationStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        Collection<VisaFee> visaFees = VisaFeeCalculation.execute(step);

        assertThat(visaFees, containsInAnyOrder(
               // new VisaFee(20, 300),
               // new VisaFee(21, 100),

                new VisaFee(1, 106),
                new VisaFee(1, 50), // TODO duplicate!
                new VisaFee(2, 64),
                new VisaFee(3, 32),
                new VisaFee(4, 60),
                new VisaFee(4, 105) // TODO duplicate
        ));
    }

}
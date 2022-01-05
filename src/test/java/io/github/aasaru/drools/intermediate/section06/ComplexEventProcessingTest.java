package io.github.aasaru.drools.intermediate.section06;

import io.github.aasaru.drools.intermediate.TestUtil;
import io.github.aasaru.drools.intermediate.domain.cep.Call;
import io.github.aasaru.drools.intermediate.service.AgentService;
import io.github.aasaru.drools.intermediate.service.CallService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.aasaru.drools.intermediate.TestUtil.emulateInputFromKeyboard;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ComplexEventProcessingTest {

    @Test
    void testStep1() {
        int step = 1;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        ComplexEventProcessing.execute(step, callService, agentService);

        KieSession ksession = TestUtil.getKieSession("ComplexEventProcessingStep", step);

        List<String> firstCalls = asList(callService.connectedCalls.get(0).getCallerPhoneNumber(), callService.connectedCalls.get(1).getCallerPhoneNumber());
        assertThat(firstCalls, containsInAnyOrder("+1111", "+2222"));

        List<String> connectedCallers = callService.connectedCalls.stream().map(Call::getCallerPhoneNumber).collect(Collectors.toList());
        assertThat(connectedCallers, not(contains("+3333")));
    }

    @Test
    void testStep2() {
        int step = 2;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        ComplexEventProcessing.execute(step, callService, agentService);

        KieSession ksession = TestUtil.getKieSession("ComplexEventProcessingStep", step);
        assertThat(callService.droppedCalls.get(0).getCallerPhoneNumber(), is("+3333"));
    }

    @Test
    void testStep3() {
        int step = 3;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        ComplexEventProcessing.execute(step, callService, agentService);

        KieSession ksession = TestUtil.getKieSession("ComplexEventProcessingStep", step);

        assertThat(callService.connectedCalls.get(0).getCallerPhoneNumber(), is("+1111"));
        assertThat(callService.connectedCalls.get(1).getCallerPhoneNumber(), is("+2222"));

        assertThat(callService.droppedCalls.get(0).getCallerPhoneNumber(), is("+3333"));
    }

    @Test
    void testStep4_passiveMode() {
        int step = 4;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        emulateInputFromKeyboard("no");
        ComplexEventProcessing.execute(step, callService, agentService);

        assertThat(callService.reportedQueueSizes.get(0), is(0));
        assertThat(callService.reportedQueueSizes.get(1), is(1));
        assertThat(callService.reportedQueueSizes.get(2), is(3));
    }

    @Test
    void testStep4_activeMode() {
        int step = 4;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        emulateInputFromKeyboard("yes");
        ComplexEventProcessing.execute(step, callService, agentService);

        assertThat(callService.reportedQueueSizes.get(0), is(0));
        assertThat(callService.reportedQueueSizes.get(1), is(1));
        assertThat(callService.reportedQueueSizes.get(2), is(2));
    }

    @Test
    void testStep5() {
        int step = 5;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        ComplexEventProcessing.execute(step, callService, agentService);

        assertThat(agentService.reportedAgentStats.get(0), is("Available agents speaking French: 0"));
        assertThat(agentService.reportedAgentStats.get(1), is("Available agents speaking French: 1"));
        assertThat(agentService.reportedAgentStats.get(2), is("Available agents speaking French: 0"));
        assertThat(agentService.reportedAgentStats.get(3), is("Available agents speaking French: 1"));
        assertThat(agentService.reportedAgentStats.get(4), is("Available agents speaking French: 0"));
        assertThat(agentService.reportedAgentStats.get(5), is("Available agents speaking French: 1"));
        assertThat(agentService.reportedAgentStats.get(6), is("All 2 German speaking agents are currently available."));
        assertThat(agentService.reportedAgentStats.get(7), is("Available agents speaking French: 0"));
    }

    @Test
    void testStep6() {
        int step = 6;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        ComplexEventProcessing.execute(step, callService, agentService);

        assertThat(callService.reportedQueueSizes.size(), is(1));
    }

    @Test
    void testStep7() {
        int step = 7;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        ComplexEventProcessing.execute(step, callService, agentService);

        assertThat(callService.reportedQueueSizes.size(), is(11));
    }

    @Test
    void testStep8() {
        int step = 8;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        ComplexEventProcessing.execute(step, callService, agentService);

        assertThat(callService.reportedQueueSizes.get(0), is(0));
        assertThat(callService.reportedQueueSizes.get(1), is(2));
        assertThat(callService.reportedQueueSizes.get(2), is(0));
    }

    @Test
    void testStep9() {
        int step = 9;

        String kieSessionName = "ComplexEventProcessingStep" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        CallService callService = new CallService();
        AgentService agentService = new AgentService();

        ComplexEventProcessing.execute(step, callService, agentService);

        assertThat(callService.reportedQueueSizes.get(0), is(0));

    }

    @AfterEach
    void tearDown() {
        try {
            ComplexEventProcessing.droolsThread.interrupt();
        }
        catch (Exception e) {
            System.out.println("problem with interrupt" + e);
        }
    }

}
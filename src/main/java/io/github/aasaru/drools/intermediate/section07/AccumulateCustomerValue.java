package io.github.aasaru.drools.intermediate.section07;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.cep.Agent;
import io.github.aasaru.drools.intermediate.domain.cep.Call;
import io.github.aasaru.drools.intermediate.section06.DroolsThread;
import io.github.aasaru.drools.intermediate.section06.FiringUntilHaltDroolsThread;
import io.github.aasaru.drools.intermediate.service.AgentService;
import io.github.aasaru.drools.intermediate.service.CallService;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;

public class AccumulateCustomerValue {

    // TODO java 11 or 17?

    // TODO move to agent repo
    public static final Agent AGENT_MARTINA = new Agent("Martina", Arrays.asList("German", "Japanese"));
    public static final Agent AGENT_BOB = new Agent("Bob", Arrays.asList("English", "French"));
    public static final Agent AGENT_DAVE = new Agent("Dave", Arrays.asList("French", "English"));
    public static final Agent AGENT_PIERRE = new Agent("Pierre", Arrays.asList("French", "German"));

    public static void main(String[] args) {
        execute(Common.promptForStep(7, args, 1, 9), new CallService(), new AgentService());
    }

    static DroolsThread droolsThread;

    static void execute(int step, CallService callService, AgentService agentService) {

        System.out.println("Running in active mode.");
        droolsThread = new FiringUntilHaltDroolsThread(step, "AccumulateCustomerValueStep");

        KieSession kieSession = droolsThread.getKieSession();


        kieSession.setGlobal("callService", callService);
        kieSession.setGlobal("agentService", agentService);


        droolsThread.start();

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+1111", "French", 1));

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+2222", "English",2));

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+3333", "English",6));

        sleepMs(300);

        droolsThread.addFactToSession(AGENT_MARTINA);

        sleepMs(300);

        droolsThread.addFactToSession(AGENT_DAVE);

        sleepMs(300);

        droolsThread.addFactToSession(AGENT_BOB);

        sleepMs(300);

        droolsThread.addFactToSession(AGENT_PIERRE);

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+4444", "German", 2));

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+5555", "German", 6));

        sleepMs(1000);

    }

    public static void sleepMs(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep interrupted");
        }
    }

}


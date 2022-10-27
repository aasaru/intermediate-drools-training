package io.github.aasaru.drools.intermediate.section06;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.cep.Call;
import io.github.aasaru.drools.intermediate.section05.internal.DroolsThread;
import io.github.aasaru.drools.intermediate.section05.internal.FiringUntilHaltDroolsThread;
import io.github.aasaru.drools.intermediate.section05.internal.TimeUtil;
import io.github.aasaru.drools.intermediate.service.AgentService;
import io.github.aasaru.drools.intermediate.service.CallService;
import org.kie.api.runtime.KieSession;

import static io.github.aasaru.drools.intermediate.repository.AgentRepository.*;

public class AccumulateCustomerValue {

    public static void main(String[] args) {
        execute(Common.promptForStep(7, args, 1, 9), new CallService(), new AgentService());
    }

    static DroolsThread droolsThread;

    static void execute(int step, CallService callService, AgentService agentService) {
        TimeUtil.resetClock();
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

        droolsThread.addFactToSession(MARTINA);

        sleepMs(300);

        droolsThread.addFactToSession(DAVE);

        sleepMs(300);

        droolsThread.addFactToSession(BOB);

        sleepMs(300);

        droolsThread.addFactToSession(PIERRE);

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


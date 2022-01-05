package io.github.aasaru.drools.intermediate.section06;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.cep.Agent;
import io.github.aasaru.drools.intermediate.domain.cep.Call;
import io.github.aasaru.drools.intermediate.service.AgentService;
import io.github.aasaru.drools.intermediate.service.CallService;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;

public class ComplexEventProcessing {

    // TODO move to agent repo
    public static final Agent MARTINA = new Agent("Martina", Arrays.asList("German", "Japanese"));
    public static final Agent BOB = new Agent("Bob", Arrays.asList("English", "French"));
    public static final Agent DAVE = new Agent("Dave", Arrays.asList("French", "English"));
    public static final Agent PIERRE = new Agent("Pierre", Arrays.asList("French", "German"));

    public static void main(String[] args) {
        execute(Common.promptForStep(6, args, 1, 9), new CallService(), new AgentService());
    }

    static DroolsThread droolsThread;

    static void execute(int step, CallService callService, AgentService agentService) {
        boolean activeMode = (step >= 5);

        if (step == 4) {
            System.out.println("here...");
            if (Common.promptForYesNoQuestion("Do you want to run in active mode?")) {
                activeMode = true;
            }
        }

        if (activeMode) {
            System.out.println("Running in active mode.");
        }
        else {
            System.out.println("Running in passive mode.");

        }




        if (activeMode) {
            droolsThread = new FiringUntilHaltDroolsThread(step, "ComplexEventProcessingStep");
        }
        else {
            droolsThread = new PeriodicallyFiringDroolsThread(step);
        }
        KieSession kieSession = droolsThread.getKieSession();

        kieSession.setGlobal( "callService", callService);
        if (step >= 2) {
            kieSession.setGlobal( "agentService", agentService);
        }


        droolsThread.start();

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+1111", "French"));

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+2222", "English"));

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+3333", "Italian"));

        sleepMs(300);

        droolsThread.addFactToSession(MARTINA);

        sleepMs(300);

        droolsThread.addFactToSession(DAVE);

        sleepMs(300);

        droolsThread.addFactToSession(BOB);

        sleepMs(300);

        droolsThread.addFactToSession(PIERRE);

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+4444", "German"));

        sleepMs(300);

        droolsThread.addFactToSession(new Call("+5555", "German"));

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


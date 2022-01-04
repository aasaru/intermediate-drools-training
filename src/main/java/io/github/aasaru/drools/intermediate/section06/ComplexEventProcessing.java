package io.github.aasaru.drools.intermediate.section06;

import static io.github.aasaru.drools.intermediate.section06.ComplexEventProcessing.sleepMs;

import java.util.Arrays;

import io.github.aasaru.drools.intermediate.Common;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

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
            droolsThread = new FiringUntilHaltDroolsThread(step);
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

class PeriodicallyFiringDroolsThread extends Thread implements DroolsThread {
    int step;
    KieSession kieSession;

    public PeriodicallyFiringDroolsThread(int step) {
        this.step = step;
        kieSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("ComplexEventProcessingStep" + step);

    }

    @Override
    public KieSession getKieSession() {
        return kieSession;
    }

    @Override
    public void addFactToSession(Object o) {
        System.out.println("Inserting to session: " + o);
        kieSession.insert(o);
    }

    public void run() {
        while (true) {
            kieSession.fireAllRules();
            sleepMs(500);
        }
    }
}

class FiringUntilHaltDroolsThread extends Thread implements DroolsThread {

    int step;
    KieSession kieSession;

    public FiringUntilHaltDroolsThread(int step) {
        this.step = step;
        kieSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("ComplexEventProcessingStep" + step);
    }

    @Override
    public KieSession getKieSession() {
        return kieSession;
    }

    public void addFactToSession(Object o) {
        System.out.println("Inserting to session: " + o);
        kieSession.insert(o);
    }

    public void run() {
        System.out.println("Starting the session until halt");
        kieSession.fireUntilHalt();
    }

}
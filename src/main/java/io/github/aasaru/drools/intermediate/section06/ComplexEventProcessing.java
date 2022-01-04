package io.github.aasaru.drools.intermediate.section06;

import static io.github.aasaru.drools.intermediate.section06.ComplexEventProcessing.sleepMs;

import java.time.LocalDate;
import java.util.Arrays;

import io.github.aasaru.drools.intermediate.Common;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

public class ComplexEventProcessing {

    // TODO move to agent repo
    public static final Agent SUSAN = new Agent("Susan", Arrays.asList("German", "Japanese"));
    public static final Agent BOB = new Agent("Bob", Arrays.asList("Spanish", "French"));
    public static final Agent DAVE = new Agent("Dave", Arrays.asList("English", "French"));
    public static final Agent LEONARDO = new Agent("Leonardo", Arrays.asList("English", "Italian"));

    public static void main(String[] args) {

        int step = Common.promptForStep(6, args, 1, 12);

        boolean activeMode = (step >= 5);

        if (step == 4) {
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


        DroolsThread t;

        if (activeMode) {
            t = new FiringUntilHaltDroolsThread(step);
        }
        else {
            t = new PeriodicallyFiringDroolsThread(step);
        }



        // event - call started
        // inimene valib keele
        // duration: call_start, agent_connected

        // mida see FROM võiks küsida, mis on sellest hetkest sõltuv?
           // mis agendid on vabad?

        // siin saaks näidata seda pidevat jooksutamist

        // kõne sisse =>


          // hiljem peaks ka accumulate suutma teha
           // ja collect

        // võib-olla on


        // võib-olla peaks


        //


        t.start();

        sleepMs(300);

        t.addFactToSession(new Call("+1111111111111", "English"));

        sleepMs(300);

        t.addFactToSession(new Call("+2222222222222", "French"));

        sleepMs(300);

        t.addFactToSession(new Call("+3333333333333", "Italian"));

        sleepMs(3000);

        t.addFactToSession(SUSAN);

        sleepMs(300);

        t.addFactToSession(BOB);

        sleepMs(300);

        t.addFactToSession(DAVE);

        sleepMs(6000);

        t.addFactToSession(LEONARDO); // TODO same person is confusing?

        sleepMs(300);

        t.addFactToSession(new Call("+444444444444", "French"));

    }

    public static void sleepMs(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

class PeriodicallyFiringDroolsThread extends Thread implements DroolsThread {

    int step;
    KieSession ksession;

    public PeriodicallyFiringDroolsThread(int step) {
        this.step = step;
        ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("ComplexEventProcessingStep" + step);
        ksession.setGlobal( "callService", new CallService());
        if (step >= 2) {
            ksession.setGlobal( "agentRepository", new AgentRepository());
        }

    }

    @Override
    public void addFactToSession(Object o) {
        System.out.println("Inserting to session: " + o);
        ksession.insert(o);
    }

    public void run() {
        int i = 0;
        while (true) {
            ksession.fireAllRules();
            sleepMs(500); // meelega suur
        }
    }
}

class FiringUntilHaltDroolsThread extends Thread implements DroolsThread {

    int step;
    KieSession ksession;

    public FiringUntilHaltDroolsThread(int step) {
        this.step = step;
        ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("ComplexEventProcessingStep" + step);
        ksession.setGlobal( "callService", new CallService());
        ksession.setGlobal( "agentRepository", new AgentRepository() );

    }

    public void addFactToSession(Object o) {
        System.out.println("Inserting to session: " + o);
        ksession.insert(o);

    }

    public void run() {
        int i = 0;
        System.out.println("Starting the session until halt");
        ksession.fireUntilHalt();
            //sleepMs(100);

        //if (ksession.)

    }
}
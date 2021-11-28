package io.github.aasaru.drools.intermediate.section06;

import static io.github.aasaru.drools.intermediate.section06.ComplexEventProcessing.sleepMs;

import java.util.Arrays;
import java.util.Collection;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.VisaFee;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

public class ComplexEventProcessing {

    public static void main(String[] args) {

        int step = Common.promptForStep(6, args, 1, 12);

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

        DroolsThread t = new DroolsThread(step);
        t.start();

        sleepMs(300);

        t.addFactToSession(new Call("+1111111111111", "en"));

        sleepMs(300);

        t.addFactToSession(new Call("+2222222222222", "fr"));

        sleepMs(300);

        t.addFactToSession(new Call("+3333333333333", "en"));

        sleepMs(3000);

        t.addFactToSession(new Agent("Susan", Arrays.asList("fr", "jp")));

        sleepMs(300);

        t.addFactToSession(new Agent("Bob", Arrays.asList("sp", "fr")));

        sleepMs(300);

        t.addFactToSession(new Agent("Dave", Arrays.asList("en", "fr")));



    }

    public static void sleepMs(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

class DroolsThread extends Thread {

    int step;
    KieSession ksession;

    public DroolsThread(int step) {
        this.step = step;
        ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("ComplexEventProcessingStep" + step);
    }


    public void addFactToSession(Object o) {
        System.out.println("Inserting to session: " + o);
        ksession.insert(o);
    }


    public void run() {
        int i = 0;
        while (true) {
            ksession.fireAllRules();
            sleepMs(100);
        }
    }
}
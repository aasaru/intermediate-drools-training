package io.github.aasaru.drools.intermediate.section05.internal;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import static io.github.aasaru.drools.intermediate.section05.ComplexEventProcessing.sleepMs;
import static io.github.aasaru.drools.intermediate.section05.internal.TimeUtil.getCurrentTime;

public class PeriodicallyFiringDroolsThread extends Thread implements DroolsThread {
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
        System.out.print(getCurrentTime());

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

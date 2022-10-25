package io.github.aasaru.drools.intermediate.section05.internal;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

public class FiringUntilHaltDroolsThread extends Thread implements DroolsThread {

    int step;
    KieSession kieSession;

    public FiringUntilHaltDroolsThread(int step, String kieSessionPrefix) {
        this.step = step;
        kieSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession(kieSessionPrefix + step);
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

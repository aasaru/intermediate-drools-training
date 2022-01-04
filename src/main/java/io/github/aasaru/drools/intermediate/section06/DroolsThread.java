package io.github.aasaru.drools.intermediate.section06;

import org.kie.api.runtime.KieSession;

public interface DroolsThread {
    KieSession getKieSession();

    void addFactToSession(Object o);

    void start();

    void interrupt();
}

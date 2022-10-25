package io.github.aasaru.drools.intermediate.section05.internal;

import org.kie.api.runtime.KieSession;

public interface DroolsThread {
    KieSession getKieSession();

    void addFactToSession(Object o);

    void start();

    void interrupt();
}

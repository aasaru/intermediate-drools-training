/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.section05.internal;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import static io.github.aasaru.drools.intermediate.section05.ComplexEventProcessing.sleepMs;
import static io.github.aasaru.drools.intermediate.section05.internal.TimeUtil.getCurrentTime;

public class PeriodicallyFiringDroolsThread extends Thread implements DroolsThread {
    int step;
    boolean run = false;
    KieSession kieSession;

    public PeriodicallyFiringDroolsThread(int step) {
        this.step = step;
        kieSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("ComplexEventProcessingStep" + step);
        run = true;
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

    @Override
    public void interrupt() {
        run = false;
    }

    public void run() {
        while (run) {
            kieSession.fireAllRules();
            sleepMs(500);
        }
    }
}

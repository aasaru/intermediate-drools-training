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

import static io.github.aasaru.drools.intermediate.section05.internal.TimeUtil.getCurrentTime;

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
        System.out.print(getCurrentTime());
        System.out.println("Inserting to session: " + o);
        kieSession.insert(o);
    }

    public void run() {
        System.out.println("Starting the session until halt");
        kieSession.fireUntilHalt();
    }

}

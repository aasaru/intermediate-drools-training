/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.section05;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.domain.cep.Call;
import io.github.aasaru.drools.intermediate.section05.internal.DroolsThread;
import io.github.aasaru.drools.intermediate.section05.internal.FiringUntilHaltDroolsThread;
import io.github.aasaru.drools.intermediate.section05.internal.PeriodicallyFiringDroolsThread;
import io.github.aasaru.drools.intermediate.section05.internal.TimeUtil;
import io.github.aasaru.drools.intermediate.service.AgentService;
import io.github.aasaru.drools.intermediate.service.CallService;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

import static io.github.aasaru.drools.intermediate.repository.AgentRepository.*;
import static io.github.aasaru.drools.intermediate.section05.internal.TimeUtil.getCurrentTime;

public class ComplexEventProcessing {

    public static void main(String[] args) {
        execute(Common.promptForStep(6, args, 1, 10), new CallService(), new AgentService());
    }

    static DroolsThread droolsThread;

    static void execute(int step, CallService callService, AgentService agentService) {
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

        if (step < 10) {

            droolsThread.start();
            TimeUtil.resetClock();

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

            sleepMs(6000);

            if (step >= 8) {
                droolsThread.getKieSession().halt();
                droolsThread.getKieSession().dispose();
            }



            droolsThread.interrupt();


        }
        else if (step == 10) {
            EntryPoint callsStream = kieSession.getEntryPoint("Calls Stream");

            droolsThread.start();
            TimeUtil.resetClock();

            sleepMs(300);

            Call call_1111_French = new Call("+1111", "French");
            logInsertToEntryPoint(call_1111_French, callsStream);
            callsStream.insert(call_1111_French);

            sleepMs(300);

            Call call_2222_English = new Call("+2222", "English");
            logInsertToEntryPoint(call_2222_English, callsStream);
            callsStream.insert(call_2222_English);

            sleepMs(300);

            Call call_3333_Italian = new Call("+3333", "Italian");
            logInsertToEntryPoint(call_3333_Italian, callsStream);
            callsStream.insert(call_3333_Italian);

            sleepMs(300);

            droolsThread.addFactToSession(MARTINA);

            sleepMs(300);

            droolsThread.addFactToSession(DAVE);

            sleepMs(300);

            droolsThread.addFactToSession(BOB);

            sleepMs(300);

            droolsThread.addFactToSession(PIERRE);

            sleepMs(300);

            Call call_4444_German = new Call("+4444", "German");
            logInsertToEntryPoint(call_4444_German, callsStream);
            callsStream.insert(call_4444_German);

            sleepMs(300);

            Call call_5555_German = new Call("+5555", "German");
            logInsertToEntryPoint(call_5555_German, callsStream);
            callsStream.insert(call_5555_German);

            sleepMs(6000);
        }
    }

    private static void logInsertToEntryPoint(Call call, EntryPoint entryPoint) {
        System.out.println(getCurrentTime() + "Inserting " + call + " to '" + entryPoint + "'");
    }

    public static void sleepMs(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep interrupted");
        }
    }

}


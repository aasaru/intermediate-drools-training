/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.service;

import io.github.aasaru.drools.intermediate.domain.cep.Agent;
import io.github.aasaru.drools.intermediate.domain.cep.Call;

import java.util.ArrayList;
import java.util.List;

import static io.github.aasaru.drools.intermediate.section05.internal.TimeUtil.getCurrentTime;

public class CallService {

    public List<Call> connectedCalls = new ArrayList<>();
    public List<Call> droppedCalls = new ArrayList<>();

    public List<Integer> reportedQueueSizes = new ArrayList<>();
    public List<String> reportedMessages = new ArrayList<>();
    public List<Double> reportedWaitTimes = new ArrayList<>();

    public void connectWithAgent(Call call, Agent agent) {
        System.out.print(getCurrentTime());
        System.out.println(">> Connecting " + agent + " with " + call);

        call.setAgent(agent);
        connectedCalls.add(call);
    }

    public void dropCall(Call call, String dropReason) {
        System.out.print(getCurrentTime());
        System.out.println("<< Dropping " + call + " because " + dropReason);
        call.setDropReason(dropReason);
        droppedCalls.add(call);
    }

    public void reportQueueSize(Integer queueSize) {
        System.out.print(getCurrentTime());
        System.out.println( "-- Queue size: " + queueSize);
        reportedQueueSizes.add(queueSize);
    }

    public void reportQueueSizeAndWaitTime(Integer queueSize, Double averageWaitTime) {
        System.out.print(getCurrentTime());
        System.out.println( "-- Queue size: " + queueSize + " and average wait time: " + averageWaitTime + "");
        reportedQueueSizes.add(queueSize);
        reportedWaitTimes.add(averageWaitTime);
    }

    public void reportMessage(String message) {
        System.out.print(getCurrentTime());
        System.out.println(message);
        reportedMessages.add(message);
    }

}

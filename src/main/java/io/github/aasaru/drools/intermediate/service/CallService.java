package io.github.aasaru.drools.intermediate.service;

import io.github.aasaru.drools.intermediate.domain.cep.Agent;
import io.github.aasaru.drools.intermediate.domain.cep.Call;

import java.util.ArrayList;
import java.util.List;

public class CallService {

    public List<Call> connectedCalls = new ArrayList<>();
    public List<Call> droppedCalls = new ArrayList<>();

    public List<Integer> reportedQueueSizes = new ArrayList<>();

    public void connectWithAgent(Call call, Agent agent) {
        System.out.println("Connecting " + call + " with " + agent);

        call.setAgent(agent);
        connectedCalls.add(call);

        // actual connecting logic would be here
    }

    public void dropCall(Call call, String dropReason) {
        System.out.println("Dropping " + call + " because " + dropReason);
        call.setDropReason(dropReason);
        droppedCalls.add(call);
    }

    public void reportQueueSize(Integer queueSize) {
        System.out.println( "Queue size: " + queueSize);
        reportedQueueSizes.add(queueSize);
    }

}

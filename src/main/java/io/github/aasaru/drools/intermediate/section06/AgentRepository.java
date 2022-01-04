package io.github.aasaru.drools.intermediate.section06;

import static io.github.aasaru.drools.intermediate.section06.ComplexEventProcessing.BOB;
import static io.github.aasaru.drools.intermediate.section06.ComplexEventProcessing.DAVE;
import static io.github.aasaru.drools.intermediate.section06.ComplexEventProcessing.SUSAN;
import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.List;

public class AgentRepository {

    // TODO use java code to add here
    public List<Agent> getAgentsAtWork() {
        return asList(SUSAN, DAVE, BOB);
    }

}

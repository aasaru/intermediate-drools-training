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

import java.util.ArrayList;
import java.util.List;

import static io.github.aasaru.drools.intermediate.repository.AgentRepository.*;
import static io.github.aasaru.drools.intermediate.section05.internal.TimeUtil.getCurrentTime;
import static java.util.Arrays.asList;

public class AgentService {

    public List<String> reportedAgentStats = new ArrayList<>();

    public List<Agent> getAgentsAtWork() {
        return asList(MARTINA, DAVE, BOB, PIERRE);
    }

    public void reportAgentStats(String message) {
        System.out.print(getCurrentTime());
        System.out.println(message);
        reportedAgentStats.add(message);
    }

}

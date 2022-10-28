/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.repository;

import io.github.aasaru.drools.intermediate.domain.cep.Agent;

import java.util.Arrays;

public class AgentRepository {
    public static final Agent MARTINA = new Agent("Martina", Arrays.asList("German", "Japanese"));
    public static final Agent BOB = new Agent("Bob", Arrays.asList("English", "French"));
    public static final Agent DAVE = new Agent("Dave", Arrays.asList("French", "English"));
    public static final Agent PIERRE = new Agent("Pierre", Arrays.asList("French", "German"));

}

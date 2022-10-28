/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.domain.cep;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;

public class Agent {

    String name;

    Collection<String> speaksLanguages;

    public Agent(String name, Collection<String> speaksLanguages) {
        this.name = name;
        this.speaksLanguages = speaksLanguages;
    }

    public boolean speaks(String language) {
        return speaksLanguages.contains(language);
    }

    public Collection<String> getSpeaksLanguages() {
        return speaksLanguages;
    }

    @Override
    public String toString() {
        return "Agent{" +
             "" + name +
             ", who speaks " + String.join(", ", speaksLanguages) +
             '}';
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}

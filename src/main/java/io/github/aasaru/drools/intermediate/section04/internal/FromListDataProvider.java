/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.section04.internal;

import org.drools.template.DataProvider;

import java.util.List;

public class FromListDataProvider implements DataProvider {

    private List<String[]> data;
    int i;

    public FromListDataProvider(List<String[]> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return i < data.size();
    }

    @Override
    public String[] next() {
        return data.get(i++);
    }
}

/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.section04;

import io.github.aasaru.drools.intermediate.Common;
import io.github.aasaru.drools.intermediate.TestUtil;
import io.github.aasaru.drools.intermediate.domain.visa.VisaVerdict;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DetermineVisaVerdictTest {
    @BeforeEach
    void before() {
        Common.disposeSession = false;
    }

    @Test
    void testStep1() {
        int step = 1;
        String kieSessionName = "VisaVerdictSection04Step" + step;
        TestUtil.disposeKieSessionIfExists(kieSessionName);

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession( kieSessionName );


        DetermineVisaVerdict.executeInSession(kieSession, step);


        List<VisaVerdict> visaVerdicts = new ArrayList<>();
        TestUtil.addObjectsOfType(kieSession, visaVerdicts, VisaVerdict.class);
        List<Integer> visaApplicationIds = visaVerdicts.stream().map(VisaVerdict::getApplicationId).collect(Collectors.toList());

        assertThat(visaVerdicts.size(), is(equalTo(4)));
        assertThat( new VisaVerdict(2, "TOURIST_VISA"), is(in(visaVerdicts)));
        assertThat(visaApplicationIds, containsInAnyOrder(1, 2 ,3 ,4));
    }


}

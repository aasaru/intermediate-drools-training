/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.domain.visa;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VisaVerdict {

    private Integer applicationId;
    private String verdictCode;

    public VisaVerdict(Integer applicationId, String verdictCode) {
        this.applicationId = applicationId;
        this.verdictCode = verdictCode;
    }

    public static VisaVerdict callToInterview(Integer applicationId) {
        return new VisaVerdict(applicationId, "CALL_TO_INTERVIEW");
    }

    public static VisaVerdict issueVisa(Integer applicationId) {
        return new VisaVerdict(applicationId, "TOURIST_VISA");
    }

    public static VisaVerdict rejectVisa(Integer applicationId) {
        return new VisaVerdict(applicationId, "REJECTED");
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return "VisaVerdict(applicationId:" + applicationId + ", verdictCode:" + verdictCode + ")";
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

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

public class VisaApplication {
  private int applicationId;
  private String passportNumber;
  private boolean isUrgent;

  public int getApplicationId() {
    return applicationId;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public boolean isUrgent() {
    return isUrgent;
  }

  @Override
  public String toString() {
    return "VisaApplication(#" + applicationId + ", pass:" + passportNumber + ")";
  }

  public static VisaApplicationBuilder newBuilder() {
    return new VisaApplicationBuilder();
  }

  public static final class VisaApplicationBuilder {
    private int applicationId;
    private String passportNumber;
    private boolean isUrgent;

    private VisaApplicationBuilder() {
    }

    public VisaApplicationBuilder withApplicationId(int applicationId) {
      this.applicationId = applicationId;
      return this;
    }

    public VisaApplicationBuilder withPassportNumber(String passportNumber) {
      this.passportNumber = passportNumber;
      return this;
    }

    public VisaApplicationBuilder withIsUrgent(boolean isUrgent) {
      this.isUrgent = isUrgent;
      return this;
    }

    public VisaApplication build() {
      VisaApplication visaApplication = new VisaApplication();
      visaApplication.applicationId = applicationId;
      visaApplication.passportNumber = passportNumber;
      visaApplication.isUrgent = isUrgent;
      return visaApplication;
    }
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

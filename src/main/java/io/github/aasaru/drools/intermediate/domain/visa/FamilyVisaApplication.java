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

import java.util.Collection;
import java.util.stream.Collectors;

public class FamilyVisaApplication {
  private int applicationId;
  private int personCount;
  private boolean isUrgent;

  public FamilyVisaApplication(int applicationId) {
    this.applicationId = applicationId;
  }

  public int getApplicationId() {
    return applicationId;
  }

  public int getPersonCount() {
    return personCount;
  }

  public boolean isUrgent() {
    return isUrgent;
  }


  public static String join(Collection<String> collection) {
    return collection.stream()
      .map(Object::toString)
      .collect(Collectors.joining(","));
  }

  @Override
  public String toString() {
    return String.format("FamilyVisaApplication[#%d, personCount: %s, urgent: %s]", applicationId, personCount, isUrgent);
  }

  public static FamilyVisaApplicationBuilder newBuilder() {
    return new FamilyVisaApplicationBuilder();
  }


  public static final class FamilyVisaApplicationBuilder {
    private int applicationId;
    private int personCount;
    private boolean isUrgent;

    private FamilyVisaApplicationBuilder() {
    }

    public FamilyVisaApplicationBuilder withApplicationId(int applicationId) {
      this.applicationId = applicationId;
      return this;
    }

    public FamilyVisaApplicationBuilder withIsUrgent(boolean isUrgent) {
      this.isUrgent = isUrgent;
      return this;
    }

    public FamilyVisaApplicationBuilder withPersonCount(int personCount) {
      this.personCount = personCount;
      return this;
    }

    public FamilyVisaApplication build() {
      FamilyVisaApplication familyVisaApplication = new FamilyVisaApplication(applicationId);
      familyVisaApplication.isUrgent = this.isUrgent;
      familyVisaApplication.personCount = this.personCount;
      return familyVisaApplication;
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

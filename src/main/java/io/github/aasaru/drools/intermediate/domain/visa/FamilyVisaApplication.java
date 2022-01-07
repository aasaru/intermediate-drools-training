/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.domain.visa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyVisaApplication {
  private int applicationId;
  private List<String> passportNumbers = new ArrayList<>();
  private boolean isUrgent;

  public FamilyVisaApplication(int applicationId) {
    this.applicationId = applicationId;
  }

  public int getApplicationId() {
    return applicationId;
  }

  public List<String> getPassportNumbers() {
    return passportNumbers;
  }

  public boolean isUrgent() {
    return isUrgent;
  }

  public int getPersonCount() {
    return passportNumbers.size();
  }

  public static String join(Collection<String> collection) {
    return collection.stream()
      .map(Object::toString)
      .collect(Collectors.joining(","));
  }

  @Override
  public String toString() {
    return String.format("FamilyVisaApplication[#%d, %s]", applicationId, join(passportNumbers));
  }

  public static FamilyVisaApplicationBuilder newBuilder() {
    return new FamilyVisaApplicationBuilder();
  }


  public static final class FamilyVisaApplicationBuilder {
    private int applicationId;
    private List<String> passportNumbers = new ArrayList<>();
    private boolean isUrgent;

    private FamilyVisaApplicationBuilder() {
    }


    public FamilyVisaApplicationBuilder withApplicationId(int applicationId) {
      this.applicationId = applicationId;
      return this;
    }

    public FamilyVisaApplicationBuilder withPassportNumbers(List<String> passportNumbers) {
      this.passportNumbers = passportNumbers;
      return this;
    }

    public FamilyVisaApplicationBuilder withIsUrgent(boolean isUrgent) {
      this.isUrgent = isUrgent;
      return this;
    }

    public FamilyVisaApplication build() {
      FamilyVisaApplication FamilyVisaApplication = new FamilyVisaApplication(applicationId);
      FamilyVisaApplication.passportNumbers = this.passportNumbers;
      FamilyVisaApplication.isUrgent = this.isUrgent;
      return FamilyVisaApplication;
    }
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof FamilyVisaApplication
        && ((FamilyVisaApplication) obj).getApplicationId() == applicationId;
  }

  @Override
  public int hashCode() {
    return Long.valueOf(applicationId).hashCode();
  }

}

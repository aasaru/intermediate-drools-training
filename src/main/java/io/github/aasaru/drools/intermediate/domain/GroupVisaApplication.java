/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GroupVisaApplication {
  private int applicationId;
  private List<String> passportNumbers = new ArrayList<>();
  private LocalDate visitStartDate;
  private LocalDate visitEndDate;
  private VisaType visaType;
  private boolean isUrgent;

  private Boolean validationPassed = null;

  public GroupVisaApplication(int applicationId) {
    this.applicationId = applicationId;
  }

  public int getApplicationId() {
    return applicationId;
  }

  public List<String> getPassportNumbers() {
    return passportNumbers;
  }

  public LocalDate getVisitStartDate() {
    return visitStartDate;
  }

  public LocalDate getVisitEndDate() {
    return visitEndDate;
  }

  public Boolean getValidationPassed() {
    return validationPassed;
  }

  public void setValidationPassed(Boolean validationPassed) {
    this.validationPassed = validationPassed;
  }

  public VisaType getVisaType() {
    return visaType;
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
    return String.format("GroupVisaApplication[#%d, %s]", applicationId, join(passportNumbers));
  }

  public static GroupVisaApplicationBuilder newBuilder() {
    return new GroupVisaApplicationBuilder();
  }


  public static final class GroupVisaApplicationBuilder {
    private int applicationId;
    private List<String> passportNumbers = new ArrayList<>();
    private LocalDate visitStartDate;
    private LocalDate visitEndDate;
    private VisaType visaType;
    private boolean isUrgent;

    private GroupVisaApplicationBuilder() {
    }


    public GroupVisaApplicationBuilder withApplicationId(int applicationId) {
      this.applicationId = applicationId;
      return this;
    }

    public GroupVisaApplicationBuilder withPassportNumbers(List<String> passportNumbers) {
      this.passportNumbers = passportNumbers;
      return this;
    }

    public GroupVisaApplicationBuilder withVisitStartDate(LocalDate visitStartDate) {
      this.visitStartDate = visitStartDate;
      return this;
    }

    public GroupVisaApplicationBuilder withVisitEndDate(LocalDate visitEndDate) {
      this.visitEndDate = visitEndDate;
      return this;
    }

    public GroupVisaApplicationBuilder withEntryType(VisaType visaType) {
      this.visaType = visaType;
      return this;
    }

    public GroupVisaApplicationBuilder withIsUrgent(boolean isUrgent) {
      this.isUrgent = isUrgent;
      return this;
    }

    public GroupVisaApplication build() {
      GroupVisaApplication GroupVisaApplication = new GroupVisaApplication(applicationId);
      GroupVisaApplication.visitEndDate = this.visitEndDate;
      GroupVisaApplication.passportNumbers = this.passportNumbers;
      GroupVisaApplication.visitStartDate = this.visitStartDate;
      GroupVisaApplication.visaType = this.visaType;
      GroupVisaApplication.isUrgent = this.isUrgent;
      return GroupVisaApplication;
    }
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof GroupVisaApplication
        && ((GroupVisaApplication) obj).getApplicationId() == applicationId;
  }

  @Override
  public int hashCode() {
    return Long.valueOf(applicationId).hashCode();
  }

}

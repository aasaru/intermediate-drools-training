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

import io.github.aasaru.drools.intermediate.domain.visa.FamilyVisaApplication;
import io.github.aasaru.drools.intermediate.domain.visa.Passport;
import io.github.aasaru.drools.intermediate.domain.visa.VisaApplication;
import io.github.aasaru.drools.intermediate.domain.visa.VisaApplicationFolder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ApplicationRepository {

  public static final String SARAH_PASSPORT_NUMBER = "CA-SARAH-1";
  public static final String SIMON_PASSPORT_NUMBER = "CA-SIMON-2";
  public static final String EMILY_PASSPORT_NUMBER = "AU-EMILY-3";
  public static final String JAMES_PASSPORT_NUMBER = "AU-JAMES-4";

  public static final Passport SARAH_PASSPORT = Passport.newBuilder()
       .withPassportNumber(SARAH_PASSPORT_NUMBER)
       .withCountry("CA")
       .withName("Sarah Murphy")
       .withAge(50)
       .build();
  public static final Passport SIMON_PASSPORT = Passport.newBuilder()
       .withPassportNumber(SIMON_PASSPORT_NUMBER)
       .withCountry("CA")
       .withName("Simon Murphy")
       .withAge(12)
       .build();
  public static final Passport EMILY_PASSPORT = Passport.newBuilder()
       .withPassportNumber(EMILY_PASSPORT_NUMBER)
       .withCountry("AU")
       .withName("Emily Brown")
       .withAge(16)
       .build();
  public static final Passport JAMES_PASSPORT = Passport.newBuilder()
       .withPassportNumber(JAMES_PASSPORT_NUMBER)
       .withCountry("CA")
       .withName("James Brown")
       .withAge(17)
       .build();

  public static final VisaApplication SARAH_VISA_APPLICATION = VisaApplication.newBuilder()
       .withApplicationId(1)
       .withPassportNumber(SARAH_PASSPORT_NUMBER)
       .withIsUrgent(false)
       .build();
  public static final VisaApplication SIMON_VISA_APPLICATION = VisaApplication.newBuilder()
       .withApplicationId(2)
       .withPassportNumber(SIMON_PASSPORT_NUMBER)
       .withIsUrgent(false)
       .build();
  public static final VisaApplication EMILY_VISA_APPLICATION = VisaApplication.newBuilder()
       .withApplicationId(3)
       .withPassportNumber(EMILY_PASSPORT_NUMBER)
       .withIsUrgent(false)
       .build();
  public static final VisaApplication JAMES_VISA_APPLICATION = VisaApplication.newBuilder()
       .withApplicationId(4)
       .withPassportNumber(JAMES_PASSPORT_NUMBER)
       .withIsUrgent(true)
       .build();

  public static List<Passport> getPassports() {
    return asList(SARAH_PASSPORT, SIMON_PASSPORT, EMILY_PASSPORT, JAMES_PASSPORT);
  }

  public static List<VisaApplication> getVisaApplications() {
    return asList(SARAH_VISA_APPLICATION, SIMON_VISA_APPLICATION, EMILY_VISA_APPLICATION, JAMES_VISA_APPLICATION);
  }

  public static List<FamilyVisaApplication> getFamilyVisaApplications() {
    List<FamilyVisaApplication> familyVisaApplications = new ArrayList<>();

    familyVisaApplications.add(FamilyVisaApplication.newBuilder()
            .withApplicationId(20)
            .withPersonCount(2)
            .withIsUrgent(true)
            .build());

    familyVisaApplications.add(FamilyVisaApplication.newBuilder()
            .withApplicationId(21)
            .withPersonCount(2)
            .withIsUrgent(false)
            .build());

    familyVisaApplications.add(FamilyVisaApplication.newBuilder()
            .withApplicationId(22)
            .withPersonCount(4)
            .withIsUrgent(false)
            .build());

    return familyVisaApplications;
  }

  public static List<VisaApplicationFolder> getVisaApplicationFolders() {

    return asList(
         new VisaApplicationFolder(SARAH_PASSPORT, SARAH_VISA_APPLICATION),
         new VisaApplicationFolder(SIMON_PASSPORT, SIMON_VISA_APPLICATION),
         new VisaApplicationFolder(EMILY_PASSPORT, EMILY_VISA_APPLICATION),
         new VisaApplicationFolder(JAMES_PASSPORT, JAMES_VISA_APPLICATION)
    );

  }

  public static List<VisaApplicationFolder> getVisaApplicationFoldersWithBookings() {

    return asList(
            new VisaApplicationFolder(SARAH_PASSPORT, SARAH_VISA_APPLICATION, true, true),
            new VisaApplicationFolder(SIMON_PASSPORT, SIMON_VISA_APPLICATION, false, false),
            new VisaApplicationFolder(EMILY_PASSPORT, EMILY_VISA_APPLICATION, false, true),
            new VisaApplicationFolder(JAMES_PASSPORT, JAMES_VISA_APPLICATION, true, false)
    );

  }

}

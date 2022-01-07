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

import java.time.LocalDate;
import java.util.Objects;

public class Passport {
  private String passportNumber;
  private String name;
  private int age;

  private String cause = "";

  private Passport() {
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCountry() {
    return this.passportNumber.substring(0,2);
  }

  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  @Override
  public String toString() {
    return String.format("Passport[no:%s, name:%s, age:%d]", passportNumber, name, age);
  }

  public static PassportBuilder newBuilder() {
    return new PassportBuilder();
  }

   public static final class PassportBuilder {
    private String passportNumber;
    private String name;
    private LocalDate expiresOn;
    private int unusedVisaPages;
    private int age;

    private PassportBuilder() {
    }

    public PassportBuilder withPassportNumber(String passportNumber) {
      this.passportNumber = passportNumber;
      return this;
    }

    public PassportBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public PassportBuilder withExpiresOn(LocalDate expiresOn) {
      this.expiresOn = expiresOn;
      return this;
    }

    public PassportBuilder withUnusedVisaPages(int unusedVisaPages) {
      this.unusedVisaPages = unusedVisaPages;
      return this;
    }

    public PassportBuilder withAge(int age) {
      this.age = age;
      return this;
    }

    public Passport build() {
      Passport passport = new Passport();
      passport.passportNumber = passportNumber;
      passport.name = name;
      passport.age = age;
      return passport;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Passport)) return false;
    Passport passport = (Passport) o;
    return age == passport.age &&
        Objects.equals(passportNumber, passport.passportNumber) &&
        Objects.equals(name, passport.name) &&
        Objects.equals(cause, passport.cause);
  }

  @Override
  public int hashCode() {
    return Objects.hash(passportNumber, name, age, cause);
  }

}

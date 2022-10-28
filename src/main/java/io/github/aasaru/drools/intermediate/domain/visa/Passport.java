/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

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

public class Passport {
  private String passportNumber;
  private String name;
  private int age;
  private String country;

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
    return country;
  }


  @Override
  public String toString() {
    return String.format("Passport[no:%s, country:%s, name:%s, age:%d]", passportNumber, country, name, age);
  }

  public static PassportBuilder newBuilder() {
    return new PassportBuilder();
  }

   public static final class PassportBuilder {
    private String passportNumber;
    private String name;
    private int age;
    private String country;

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

    public PassportBuilder withAge(int age) {
      this.age = age;
      return this;
    }

    public PassportBuilder withCountry(String country) {
      this.country = country;
      return this;
    }

    public Passport build() {
      Passport passport = new Passport();
      passport.passportNumber = passportNumber;
      passport.name = name;
      passport.age = age;
      passport.country = country;
      return passport;
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

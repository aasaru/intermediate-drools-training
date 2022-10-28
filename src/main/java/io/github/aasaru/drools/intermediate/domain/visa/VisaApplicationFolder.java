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

import static java.util.Arrays.asList;

public class VisaApplicationFolder {

    private Passport passport;
    private VisaApplication visaApplication;
    private Integer visaFee;

    private Boolean booking;

    private Boolean unemployed;

    public VisaApplicationFolder(Passport passport, VisaApplication visaApplication) {
        if (!passport.getPassportNumber().equals(visaApplication.getPassportNumber())) {
            throw new IllegalArgumentException("Passport number doesn't match with passport number in visa application");
        }

        this.visaApplication = visaApplication;
        this.passport = passport;
    }

    public VisaApplicationFolder(Passport passport, VisaApplication visaApplication, Boolean unemployed, Boolean booking) {
        this(passport, visaApplication);
        this.unemployed = unemployed;
        this.booking = booking;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public VisaApplication getVisaApplication() {
        return visaApplication;
    }

    public Integer getVisaApplicationId(){
        return visaApplication.getApplicationId();
    }

    public void setVisaApplication(VisaApplication visaApplication) {
        this.visaApplication = visaApplication;
    }

    public boolean isBooking() {
        return booking != null && booking;
    }

    public Boolean getUnemployed() {
        return unemployed;
    }

    public void setVisaFee(Integer visaFee) {
        this.visaFee = visaFee;
    }

    public void addVisaFee(Integer visaFee) {
        this.visaFee = (this.visaFee == null) ?visaFee : (this.visaFee + visaFee);
    }

    public Integer getVisaFee() {
        return visaFee;
    }

    public boolean countryIn(String... countries) {


        boolean val = countries != null && asList(countries).contains(passport.getCountry());

        System.out.println("Testing " + passport + " against " + String.join(",", countries) + " and returning " + val);

        return val;
    }

    @Override
    public String toString() {
        return "VisaApplicationFolder(#" + visaApplication.getApplicationId()
                + ", urgent:" + visaApplication.isUrgent()
                + ", age:" + passport.getAge()
                + ", country:" + passport.getCountry()
                + ((visaFee == null) ?"" :", visaFee="+visaFee)
                + ((booking == null) ?"" :(booking) ?", hasBooking" :", noBooking")
                + ((unemployed == null) ?"" :(unemployed) ?", unemployed" :"")
                + ")";
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

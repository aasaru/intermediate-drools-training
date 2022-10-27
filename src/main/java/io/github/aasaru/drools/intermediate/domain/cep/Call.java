package io.github.aasaru.drools.intermediate.domain.cep;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Duration;
import java.time.LocalDateTime;

public class Call {

    int customerValue;

    String callerPhoneNumber;

    LocalDateTime callStart;

    String language;

    // agent==null means this call is in waiting line
    Agent agent;

    String dropReason;

    public Call(String callerPhoneNumber, String language) {
        this.callerPhoneNumber = callerPhoneNumber;
        this.language = language;
        this.customerValue = 0;
        this.callStart = LocalDateTime.now();
    }

    public Call(String callerPhoneNumber, String language, int customerValue) {
        this.customerValue = customerValue;
        this.callerPhoneNumber = callerPhoneNumber;
        this.language = language;
        this.callStart = LocalDateTime.now();
    }

    public String getLanguage() {
        return language;
    }

    public LocalDateTime getCallStart() {
        return callStart;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void setDropReason(String dropReason) {
        this.dropReason = dropReason;
    }

    public String getCallerPhoneNumber() {
        return callerPhoneNumber;
    }

    public int getCustomerValue() {
        return customerValue;
    }

    public long getTimeWaited() {
        return Duration.between(callStart, LocalDateTime.now()).toMillis()/100;
    }

    @Override
    public String toString() {
        return "Call{" +
             "from number " + callerPhoneNumber +
             ", in " + language +
             (customerValue == 0 ?"" :",customerValue="+customerValue) +
             '}';
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

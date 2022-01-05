package io.github.aasaru.drools.intermediate.domain.cep;

import java.time.LocalDateTime;

// TODO builder
public class Call {

    int customerValue;

    String callerPhoneNumber;

    LocalDateTime callStart;

    String language;

    // if agent==null => is in waiting line
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

    @Override
    public String toString() {
        return "Call{" +
             "callerPhoneNumber='" + callerPhoneNumber + '\'' +
             ",language='" + language + '\'' +
             (customerValue == 0 ?"" :",customerValue="+customerValue) +
             '}';
    }
}

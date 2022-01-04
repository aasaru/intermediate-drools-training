package io.github.aasaru.drools.intermediate.section06;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Call {

    String callerPhoneNumber;

    // start time
    LocalDateTime callStart;
    LocalDateTime agentConnected;

    // customer selected
    String language; // en, ru, et

    // if agent==null => is in waiting line
    Agent agent;

    String dropReason;

    public Call(String callerPhoneNumber, String lang) {
        this.callerPhoneNumber = callerPhoneNumber;
        this.language = lang;
        this.callStart = LocalDateTime.now();
    }

    public String getLanguage() {
        return language;
    }

    public LocalDateTime getCallStart() {
        return callStart;
    }

    public Long getDurationInNanoSeconds() {
        return ChronoUnit.NANOS.between(callStart, LocalDateTime.now());
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

    @Override
    public String toString() {
        return "Call{" +
             "callerPhoneNumber='" + callerPhoneNumber + '\'' +
             ",language='" + language + '\'' +
             '}';
    }
}

package io.github.aasaru.drools.intermediate.section06;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import sun.management.Agent;

public class Call {

    String callerPhoneNumber;

    // start time
    LocalDateTime callStart;
    LocalDateTime agentConnected;

    // customer selected
    String language; // en, ru, et

    // if agent==null => is in waiting line
    Agent agent;

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

    @Override
    public String toString() {
        return "Call{" +
             "callerPhoneNumber='" + callerPhoneNumber + '\'' +
             ",language='" + language + '\'' +
             '}';
    }
}

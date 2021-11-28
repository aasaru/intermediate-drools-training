package io.github.aasaru.drools.intermediate.section06;

import java.util.Collection;
import java.util.stream.Collectors;

public class Agent {

    String name;

    Collection<String> speaksLanguages;

    // set to true if agent want's to take a break after the ongoing call
    boolean goesAway = false;

    public Agent(String name, Collection<String> speaksLanguages) {
        this.name = name;
        this.speaksLanguages = speaksLanguages;
    }

    public boolean speaks(String language) {
        return speaksLanguages.contains(language);
    }

    public Collection<String> getSpeaksLanguages() {
        return speaksLanguages;
    }

    @Override
    public String toString() {
        return "Agent{" +
             "name='" + name + '\'' +
             ",speaksLanguages='" + String.join(",", speaksLanguages) + '\'' +
             '}';
    }
}

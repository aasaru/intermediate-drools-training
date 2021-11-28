package io.github.aasaru.drools.intermediate.domain;

import java.time.LocalDate;

public interface GeneralVisaApplication {
    int getApplicationId();

    LocalDate getVisitStartDate();

    LocalDate getVisitEndDate();

    boolean isUrgent();
}

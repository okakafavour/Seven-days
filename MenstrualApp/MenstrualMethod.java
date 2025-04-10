package MenstrualApp;

import java.time.LocalDate;
import java.util.*;
public class MenstrualMethod {
    private LocalDate startDate;
    private LocalDate endDate;
    private int cycleLength;

    public MenstrualMethod(LocalDate startDate, LocalDate endDate, int cycleLength) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cycleLength = cycleLength;
    }

    public LocalDate getOvulationDate() {
        return endDate.plusDays(cycleLength/2);
    }
    public LocalDate getSafePeriodStartDate() {
        return endDate.plusDays(1);
    }
    public LocalDate getSafePeriodEndDate() {
        return endDate.plusDays(6);
    }
    public LocalDate getNextPeriodStart(){
        return endDate.plusDays(cycleLength);
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
}
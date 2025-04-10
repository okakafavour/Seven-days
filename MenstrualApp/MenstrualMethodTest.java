package MenstrualApp;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class MenstrualMethodTest {

    @Test
    public void test_getOvulationDate() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 28);
        int cycleLength = 28;

        MenstrualMethod cycle = new MenstrualMethod(startDate, endDate, cycleLength);

        LocalDate expectedOvulation = endDate.plusDays(14);
        assertEquals(expectedOvulation, cycle.getOvulationDate());
    }

    @Test
    public void test_getSafePeriodStartDate() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 28);
        int cycleLength = 28;

        MenstrualMethod cycle = new MenstrualMethod(startDate, endDate, cycleLength);

        LocalDate expectedSafeStart = endDate.plusDays(1);
        assertEquals(expectedSafeStart, cycle.getSafePeriodStartDate());
    }

    @Test
    public void test_getSafePeriodEndDate() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 28);
        int cycleLength = 28;

        MenstrualMethod cycle = new MenstrualMethod(startDate, endDate, cycleLength);

        LocalDate expectedSafeEnd = endDate.plusDays(6);
        assertEquals(expectedSafeEnd, cycle.getSafePeriodEndDate());
    }

    @Test
    public void test_getNextPeriodStart() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 28);
        int cycleLength = 28;

        MenstrualMethod cycle = new MenstrualMethod(startDate, endDate, cycleLength);

        LocalDate expectedNextPeriod = endDate.plusDays(cycleLength);
        assertEquals(expectedNextPeriod, cycle.getNextPeriodStart());
    }

    @Test
    public void test_getStartDate() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 28);
        int cycleLength = 28;

        MenstrualMethod cycle = new MenstrualMethod(startDate, endDate, cycleLength);

        assertEquals(startDate, cycle.getStartDate());
    }

    @Test
    public void test_getEndDate() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 28);
        int cycleLength = 28;

        MenstrualMethod cycle = new MenstrualMethod(startDate, endDate, cycleLength);

        assertEquals(endDate, cycle.getEndDate());
    }

    @Test
    public void test_getOvulationDate_withDifferentCycleLength() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 28);
        int cycleLength = 30;

        MenstrualMethod cycle = new MenstrualMethod(startDate, endDate, cycleLength);

        LocalDate expectedOvulation = endDate.plusDays(15);
        assertEquals(expectedOvulation, cycle.getOvulationDate());
    }
}
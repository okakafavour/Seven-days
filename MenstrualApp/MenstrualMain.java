package MenstrualApp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class MenstrualMain {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner input = new Scanner(System.in);

        System.out.println("===== Welcome to Bokku Menstrual App =====");

        LocalDate startdate = null;
        while(startdate == null){
            System.out.print("Enter start date of your last period: ");
            String startDateInput = input.nextLine();

            try{
                startdate = LocalDate.parse(startDateInput, formatter);
            } catch (Exception e){
                System.out.println("Invalid date format");
            }
        }

        LocalDate endDate = null;
        while(endDate == null){
            System.out.print("Enter end date of your last period: ");
            String endDateInput = input.nextLine();

            try {
                endDate = LocalDate.parse(endDateInput, formatter);
                if(endDate.isBefore(startdate)){
                    System.out.println("End date is after start date");
                }

            } catch (Exception e){
                System.out.println("Invalid date format");
            }
        }

        int cycle = 0;
        while(cycle <= 0){
            System.out.print("Enter your cycle length (in days): ");
            try {
                int cycleLength = input.nextInt();
                 if(cycleLength <= 0){
                     System.out.println("Number must be greather than zero");
                 }
            } catch (Exception e){
                System.out.println("Invalid cycle length");
            }
        }

        MenstrualMethod main = new MenstrualMethod(startdate, endDate, cycle);

        System.out.println("your last start date is: " + main.getStartDate());
        System.out.println("your last end date is: " + main.getEndDate());
        System.out.println("Your Ovulation date is: " + main.getOvulationDate());
        System.out.println("Your safe period start date is: " + main.getSafePeriodStartDate());
        System.out.println("Your safe period end date is: " + main.getSafePeriodEndDate());
        System.out.println("Your Next period is: " + main.getNextPeriodStart());

    }
}

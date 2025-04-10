package MenstrualApp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class MenstrualMain {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner input = new Scanner(System.in);

        LocalDate startdate == null;
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
            } catch (Exception e){
                System.out.println("Invalid date format");
            }
        }

        int cycle = 0;
        while(cycle <= 0){
            System.out.print("Enter your cycle length (in days): ");
            int cycleLength = input.nextInt();

            try {
                Sys
            }
        }




    }
}

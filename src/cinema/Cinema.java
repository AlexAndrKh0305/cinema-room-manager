package cinema;

import java.util.Locale;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        // Reading data
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int numberOfSeatsInRow = scanner.nextInt();

        // Calculation of total income
        int totalIncome;
        if (numberOfRows * numberOfSeatsInRow <= 60) {
            totalIncome = numberOfRows * numberOfSeatsInRow * 10;
        } else {
            int rowsInFrontHalf = numberOfRows / 2;
            int rowsInBackHalf = (numberOfRows + 1) / 2;
            totalIncome = (rowsInFrontHalf * 10 + rowsInBackHalf * 8) * numberOfSeatsInRow;
        }

        // Printing data
        System.out.println("Total income:");
        System.out.println("$" + totalIncome);
    }
}

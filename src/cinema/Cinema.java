package cinema;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        // Reading dimensions of screen room
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int numberOfSeatsInRow = scanner.nextInt();
        System.out.println();

        // Creating screen room
        char[][] screenRoom = new char[numberOfRows][numberOfSeatsInRow];
        for (int i = 0; i < numberOfRows; i++) {
            Arrays.fill(screenRoom[i], 'S');
        }

        // Printing all the seats
        System.out.print("Cinema:\n ");
        for (int i = 1; i <= numberOfSeatsInRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                System.out.print(" " + screenRoom[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        // Reading reserved seat
        System.out.println("Enter a row number:");
        System.out.print("> ");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        System.out.print("> ");
        int seatNumber = scanner.nextInt();
        System.out.println();

        // Marking chosen seat
        screenRoom[rowNumber - 1][seatNumber - 1] = 'B';

        // Counting ticket price
        int ticketPrice;
        if (numberOfRows * numberOfSeatsInRow <= 60) {
            ticketPrice = 10;
        } else {
            ticketPrice = (rowNumber <= numberOfRows / 2) ? 10 : 8;
        }

        // Printing data
        System.out.println("Ticket price: $" + ticketPrice);
        System.out.println();
        System.out.print("Cinema:\n ");
        for (int i = 1; i <= numberOfSeatsInRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                System.out.print(" " + screenRoom[i][j]);
            }
            System.out.println();
        }

    }
}

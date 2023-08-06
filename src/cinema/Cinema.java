package cinema;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Cinema {

    public static char[][] createScreenRoom(int numberOfRows, int numberOfSeatsInRow) {
        char[][] screenRoom = new char[numberOfRows][numberOfSeatsInRow];
        for (int i = 0; i < numberOfRows; i++) {
            Arrays.fill(screenRoom[i], 'S');
        }
        return screenRoom;
    }

    public static void showSeats(char[][] screenRoom) {
        System.out.print("Cinema:\n ");
        for (int i = 1; i <= screenRoom[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < screenRoom.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < screenRoom[i].length; j++) {
                System.out.print(" " + screenRoom[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    public static void buyTicket(int numberOfRows, int numberOfSeatsInRow, char[][] screenRoom) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        // Reading reserved seat
        System.out.println("Enter a row number:");
        System.out.print("> ");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        System.out.print("> ");
        int seatNumber = scanner.nextInt();

        // Marking chosen seat
        screenRoom[rowNumber - 1][seatNumber - 1] = 'B';

        int ticketPrice = countTicketPrice(numberOfRows, numberOfSeatsInRow, rowNumber);
        System.out.println("Ticket price: $" + ticketPrice);
        System.out.println();
    }

    public static int countTicketPrice(int numberOfRows, int numberOfSeatsInRow, int rowNumber) {
        if (numberOfRows * numberOfSeatsInRow <= 60) {
            return 10;
        } else {
            return (rowNumber <= numberOfRows / 2) ? 10 : 8;
        }
    }

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
        char[][] screenRoom = createScreenRoom(numberOfRows, numberOfSeatsInRow);

        printMenu();
        int itemInMenu = scanner.nextInt();
        System.out.println();
        while (itemInMenu != 0) {
            switch (itemInMenu) {
                case 1:
                    showSeats(screenRoom);
                    break;
                case 2:
                    buyTicket(numberOfRows, numberOfSeatsInRow, screenRoom);
                    break;
                default:
                    System.out.println("There is no such item!");
                    break;
            }
            printMenu();
            itemInMenu = scanner.nextInt();
            System.out.println();
        }
    }
}

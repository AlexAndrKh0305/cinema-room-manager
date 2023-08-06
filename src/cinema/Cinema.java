package cinema;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Cinema {

    static final Scanner SCANNER = new Scanner(System.in).useLocale(Locale.US);
    static int NUMBER_OF_ROWS;
    static int NUMBER_OF_SEATS_IN_ROW;
    static char[][] SCREEN_ROOM;

    public static char[][] createScreenRoom() {
        char[][] screenRoom = new char[NUMBER_OF_ROWS][NUMBER_OF_SEATS_IN_ROW];
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            Arrays.fill(screenRoom[i], 'S');
        }
        return screenRoom;
    }

    public static void showSeats() {
        System.out.print("Cinema:\n ");
        for (int i = 1; i <= NUMBER_OF_SEATS_IN_ROW; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < NUMBER_OF_SEATS_IN_ROW; j++) {
                System.out.print(" " + SCREEN_ROOM[i][j]);
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

    public static void buyTicket() {

        // Reading reserved seat
        System.out.println("Enter a row number:");
        System.out.print("> ");
        int rowNumber = SCANNER.nextInt();
        System.out.println("Enter a seat number in that row:");
        System.out.print("> ");
        int seatNumber = SCANNER.nextInt();

        // Marking chosen seat
        SCREEN_ROOM[rowNumber - 1][seatNumber - 1] = 'B';

        int ticketPrice = countTicketPrice(NUMBER_OF_ROWS, NUMBER_OF_SEATS_IN_ROW, rowNumber);
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

    public static void readDimensions() {
        boolean isCorrect = false;
        while (!isCorrect) {
            try {
                System.out.println("Enter the number of rows:");
                System.out.print("> ");
                NUMBER_OF_ROWS = Integer.parseInt(SCANNER.nextLine());
                if (NUMBER_OF_ROWS < 1) {
                    System.out.println();
                    System.out.println("Incorrect value for \"Number of rows\"");
                    System.out.println();
                    continue;
                }
                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Incorrect value for \"Number of rows\"");
                System.out.println();
            }
        }
        isCorrect = false;
        while (!isCorrect) {
            try {
                System.out.println("Enter the number of seats in each row:");
                System.out.print("> ");
                NUMBER_OF_SEATS_IN_ROW = Integer.parseInt(SCANNER.nextLine());
                if (NUMBER_OF_SEATS_IN_ROW < 1) {
                    System.out.println();
                    System.out.println("Incorrect value for \"Number of seats in row\"");
                    System.out.println();
                    continue;
                }
                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Incorrect value for \"Number of seats in row\"");
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Reading dimensions of screen room
        readDimensions();

        // Creating screen room
        SCREEN_ROOM = createScreenRoom();

        printMenu();
        int itemInMenu = SCANNER.nextInt();
        System.out.println();
        while (itemInMenu != 0) {
            switch (itemInMenu) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                default:
                    System.out.println("There is no such item!");
                    break;
            }
            printMenu();
            itemInMenu = SCANNER.nextInt();
            System.out.println();
        }
    }
}

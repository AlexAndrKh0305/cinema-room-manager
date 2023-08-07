package cinema;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public final class Cinema {

    static final Scanner SCANNER = new Scanner(System.in).useLocale(Locale.US);
    static int NUMBER_OF_ROWS;
    static int NUMBER_OF_SEATS_IN_ROW;
    static char[][] SCREEN_ROOM;

    private static char[][] createScreenRoom() {
        char[][] screenRoom = new char[NUMBER_OF_ROWS][NUMBER_OF_SEATS_IN_ROW];
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            Arrays.fill(screenRoom[i], 'S');
        }
        return screenRoom;
    }

    private static void showSeats() {
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

    private static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private static void buyTicket() {

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

    private static void readDimensions() {
        NUMBER_OF_ROWS = getNumberOfRows();
        NUMBER_OF_SEATS_IN_ROW = getNumberOfSeatsInRow();
        System.out.println();
    }

    private static int getNumberOfRows() {
        boolean isCorrect = false;
        int numberOfRows = 0;
        while (!isCorrect) {
            try {
                System.out.println("Enter the number of rows:");
                System.out.print("> ");
                numberOfRows = Integer.parseInt(SCANNER.nextLine());
                if (numberOfRows < 1) {
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
        return numberOfRows;
    }

    private static int getNumberOfSeatsInRow() {
        boolean isCorrect = false;
        int numberOfSeatsInRow = 0;
        while (!isCorrect) {
            try {
                System.out.println("Enter the number of seats in each row:");
                System.out.print("> ");
                numberOfSeatsInRow = Integer.parseInt(SCANNER.nextLine());
                if (numberOfSeatsInRow < 1) {
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
        return numberOfSeatsInRow;
    }

    public static void chooseItem() {
        int itemInMenu;
        itemInMenu = getItemInMenu();
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
            itemInMenu = getItemInMenu();
        }
    }

    private static int getItemInMenu() {
        boolean isCorrect = false;
        int itemInMenu = 0;
        while (!isCorrect) {
            try {
                printMenu();
                itemInMenu = Integer.parseInt(SCANNER.nextLine());
                System.out.println();
                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Incorrect value for \"Item in menu\"");
                System.out.println();
            }
        }
        return itemInMenu;
    }

    public static void initCinema() {
        readDimensions();
        SCREEN_ROOM = createScreenRoom();
    }

    public static void main(String[] args) {

        // Cinema initialization
        initCinema();

        // Choosing item in menu
        chooseItem();
    }
}

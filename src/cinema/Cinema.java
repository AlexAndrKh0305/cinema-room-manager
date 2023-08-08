package cinema;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public final class Cinema {

    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private final int numberOfRows;
    private final int numberOfSeatsInRow;
    private final char[][] screenRoom;
    private int purchasedTickets;
    private int currentIncome;
    private final int totalIncome;

    public Cinema() {
        numberOfRows = getNumberOfRows();
        numberOfSeatsInRow = getNumberOfSeatsInRow();
        System.out.println();
        totalIncome = countTotalIncome();
        screenRoom = createScreenRoom();
    }

    private int countTotalIncome() {
        if (numberOfRows * numberOfSeatsInRow <= 60) {
            return 10 * numberOfRows * numberOfSeatsInRow;
        }
        int rowsInFrontHalf = numberOfRows / 2;
        int rowsInBackHalf = (numberOfRows + 1) / 2;
        return (10 * rowsInFrontHalf + 8 * rowsInBackHalf) * numberOfSeatsInRow;
    }

    private char[][] createScreenRoom() {
        char[][] screenRoom = new char[numberOfRows][numberOfSeatsInRow];
        for (int i = 0; i < numberOfRows; i++) {
            Arrays.fill(screenRoom[i], 'S');
        }
        return screenRoom;
    }

    private void showSeats() {
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
    }

    private void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private void buyTicket() {

        while (true) {
            int rowNumber = getRowNumber();
            int seatNumber = getSeatNumber();
            if (screenRoom[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("\nThat ticket has already been purchased!\n");
            } else {
                screenRoom[rowNumber - 1][seatNumber - 1] = 'B';
                int ticketPrice = countTicketPrice(rowNumber);
                currentIncome += ticketPrice;
                purchasedTickets++;
                System.out.printf("%nTicket price: $%d%n%n", ticketPrice);
                break;
            }
        }
    }

    private int getRowNumber() {
        boolean isCorrect = false;
        int rowNumber = 0;
        while (!isCorrect) {
            try {
                System.out.println("Enter a row number:");
                System.out.print("> ");
                rowNumber = Integer.parseInt(scanner.nextLine());
                if (rowNumber <= 0 || rowNumber > numberOfRows) {
                    System.out.println("\nIncorrect value for \"Row number\"\n");
                    continue;
                }
                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect value for \"Row number\"\n");
            }
        }
        return rowNumber;
    }

    private int getSeatNumber() {
        boolean isCorrect = false;
        int seatNumber = 0;
        while (!isCorrect) {
            try {
                System.out.println("Enter a seat number in that row:");
                System.out.print("> ");
                seatNumber = Integer.parseInt(scanner.nextLine());
                if (seatNumber <= 0 || seatNumber > numberOfSeatsInRow) {
                    System.out.println("\nIncorrect value for \"Seat number\"\n");
                    continue;
                }
                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect value for \"Seat number\"\n");
            }
        }
        return seatNumber;
    }

    private int countTicketPrice(int rowNumber) {
        if (numberOfRows * numberOfSeatsInRow <= 60) {
            return 10;
        }
        return (rowNumber <= numberOfRows / 2) ? 10 : 8;
    }

    private int getNumberOfRows() {
        boolean isCorrect = false;
        int numberOfRows = 0;
        while (!isCorrect) {
            try {
                System.out.println("Enter the number of rows:");
                System.out.print("> ");
                numberOfRows = Integer.parseInt(scanner.nextLine());
                if (numberOfRows < 1) {
                    System.out.println("\nIncorrect value for \"Number of rows\"\n");
                    continue;
                }
                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect value for \"Number of rows\"\n");
            }
        }
        return numberOfRows;
    }

    private int getNumberOfSeatsInRow() {
        boolean isCorrect = false;
        int numberOfSeatsInRow = 0;
        while (!isCorrect) {
            try {
                System.out.println("Enter the number of seats in each row:");
                System.out.print("> ");
                numberOfSeatsInRow = Integer.parseInt(scanner.nextLine());
                if (numberOfSeatsInRow < 1) {
                    System.out.println("\nIncorrect value for \"Number of seats in row\"\n");
                    continue;
                }
                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect value for \"Number of seats in row\"\n");
            }
        }
        return numberOfSeatsInRow;
    }

    public void chooseItem() {
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
                case 3:
                    showStatistics();
                    break;
                default:
                    System.out.println("There is no such item!");
                    break;
            }
            itemInMenu = getItemInMenu();
        }
    }

    private int getItemInMenu() {
        boolean isCorrect = false;
        int itemInMenu = 0;
        while (!isCorrect) {
            try {
                printMenu();
                itemInMenu = Integer.parseInt(scanner.nextLine());
                System.out.println();
                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect value for \"Item in menu\"\n");
            }
        }
        return itemInMenu;
    }

    private void showStatistics() {
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        double percent = (double) 100 * purchasedTickets / (numberOfRows * numberOfSeatsInRow);
        System.out.printf(Locale.US, "Percentage: %.2f%%%n", percent);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n%n", totalIncome);
    }

}

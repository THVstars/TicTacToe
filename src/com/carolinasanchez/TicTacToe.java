package com.carolinasanchez;

import java.util.*;

public class TicTacToe { // fields or properties -> public, class, static, private

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        Scanner scanner = new Scanner(System.in); // reads user input

        while(true) { // keeps executing until a condition within the while loop is false
            System.out.println("");
            System.out.println("Enter your placement (1-9):");
            int playerPosition = scanner.nextInt(); // read user input
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
                System.out.println("Sorry, that placement is already taken. Try again!");
                playerPosition = scanner.nextInt(); // read user input
            }

            placePiece(gameBoard, playerPosition, "player");

            System.out.println("");
            printGameBoard(gameBoard);

            checkWinner();
            if(checkWinner().length() > 0) {
                System.out.println("");
                System.out.println(checkWinner());
                break; // exits the program
            }

            Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1; // generates a random number 1-9.
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                System.out.println("Try again CPU!");
                cpuPosition = random.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPosition, "cpu");

            System.out.println("");
            printGameBoard(gameBoard);

            checkWinner();
            if(checkWinner().length() > 0) {
                System.out.println("");
                System.out.println(checkWinner());
                break; // exits the program
            }
        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) { // for each array (row) inside the gameBoard
            for(char c : row) { // for each char inside the row we're in
                System.out.print(c); // print out the char (c)
            }
            System.out.println(); // prints out a line after each row, so that it looks like a board
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String user) {

        char symbol = ' ';

        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if(user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch(position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3); // asList converts to List format
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        List rightDiagonal = Arrays.asList(1, 5, 9);
        List leftDiagonal = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>(); // List of lists, so we can iterate through all at once
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftColumn);
        winning.add(middleColumn);
        winning.add(rightColumn);
        winning.add(rightDiagonal);
        winning.add(leftDiagonal);

        for(List l : winning) { // for each list (l) inside the winning list (above)
            if(playerPositions.containsAll(l)) {
                return "Congratulations! You won!";
            } else if(cpuPositions.containsAll(l)) {
                return "Sorry! You lost!";
            } else if(playerPositions.size() + cpuPositions.size() == 9) {
                return "It's a tie!";
            }
        }

        return "";
    }
}

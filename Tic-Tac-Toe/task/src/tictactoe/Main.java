package tictactoe;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        final int size = 3;
        char[][] grid = new char[size][size];

        System.out.print("Enter cells: ");
        String userInput = scanner.nextLine();

        // Draw grid with user input
        System.out.println("---------");
        int index = 0;
        for (int row = 0; row < size; ++row)
        {
            System.out.print("| ");
            for (int col = 0; col < size; ++col)
            {
                System.out.print(userInput.charAt(index++) + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");
    }
}

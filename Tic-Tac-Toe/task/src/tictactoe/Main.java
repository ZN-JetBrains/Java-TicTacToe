package tictactoe;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String userInput = scanner.nextLine();

        final int size = 3;
        char[][] grid = convertInputToGrid(userInput, size);
        printGrid(grid, size);
    }

    /**
     * Returns a grid filled with X, O and _ chars
     * from a String
     *
     * @param aInput String: the input that will fill the 2D array
     * @param aSize int: the row and column size of the 2D array
     * @return char[][]: the 2D char array
     */
    private static char[][] convertInputToGrid(String aInput, final int aSize)
    {
        char[][] grid = new char[aSize][aSize];
        int index = 0;

        for (int row = 0; row < aSize; ++row)
        {
            for (int col = 0; col < aSize; ++col)
            {
                grid[row][col] = aInput.charAt(index++);
            }
        }

        return grid;
    }

    /**
     * Prints the contents of the 2D char array passed
     * as parameter
     *
     * @param aGrid char[][]: 2D char array filled with X, O and _ chars
     * @param aSize int: the size of the row and columns of the 2D array
     */
    private static void printGrid(char[][] aGrid, final int aSize)
    {
        System.out.println("---------");

        for (int row = 0; row < aSize; ++row)
        {
            System.out.print("| ");
            for (int col = 0; col < aSize; ++col)
            {
                System.out.print(aGrid[row][col] + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");
    }
}

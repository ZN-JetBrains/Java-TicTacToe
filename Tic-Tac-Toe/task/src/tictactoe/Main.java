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

        char playerX = 'X';

        // Stage 4
        boolean isValid = false;
        while (!isValid)
        {
            System.out.print("Enter the coordinates: ");
            int inputX;
            int inputY;

            try
            {
                inputX = scanner.nextInt();
                inputY = scanner.nextInt();
            }
            catch (Exception e)
            {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }

            int rowPosition = inputX - 1;
            int colPosition = inputY - 1;

            if (isCoordinatesValid(size, rowPosition, colPosition))
            {
                if (isOccupied(grid, rowPosition, colPosition))
                {
                    System.out.println("This cell is occupied! Choose another one!");
                }
                else
                {
                    isValid = true;
                    grid[rowPosition][colPosition] = playerX;
                }
            }
            else
            {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }

        printGrid(grid, size);

        //printGameState(grid, size);
    }

    private static boolean isCoordinatesValid(final int aSize, final int aX, final int aY)
    {
        return aX >= 0 && aX < aSize && aY >= 0 && aY < aSize;
    }

    private static boolean isOccupied(char[][] aGrid, final int aX, final int aY)
    {
        return aGrid[aX][aY] != '_';
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

    /**
     * Prints the state of the game by calling helper methods to
     * determine if there is a winner, if game is not over, if
     * something has gone wrong
     *
     * @param aGrid char[][]: the 2D array that represents the game board
     * @param aSize int: the size of the row and columns of the 2D array
     */
    private static void printGameState(char[][] aGrid, final int aSize)
    {
        final char playerX = 'X';
        final char playerO = 'O';
        final char emptySpace = '_';

        // Impossible state
        if (isVictorious(aGrid, playerX) && isVictorious(aGrid, playerO)
                || getDifference(aGrid, aSize, playerX, playerO) > 1)
        {
            System.out.println("Impossible");
            return;
        }

        // has X won?
        if (isVictorious(aGrid, playerX))
        {
            System.out.println("X wins");
            return;
        }

        // has O won?
        if (isVictorious(aGrid, playerO))
        {
            System.out.println("O wins");
            return;
        }

        // has empty spaces?
        if (!isGameOver(aGrid, aSize, emptySpace))
        {
            System.out.println("Game not finished");
            return;
        }

        // no empty spaces left and no winners => draw
        System.out.println("Draw");
    }

    /**
     * Returns if the game is over by checking if the 2D char array
     * no longer has any chars specified by the aEmptyMark parameter
     *
     * @param aGrid char[][]: the 2D char array that represents a game board
     * @param aSize int: the size of the rows and columns of the 2D array
     * @param aEmptyMark char: the char that is searched for
     * @return boolean: if game is over
     */
    private static boolean isGameOver(char[][] aGrid, final int aSize, final char aEmptyMark)
    {
        for (int column = 0; column < aSize; ++column)
        {
            for (int row = 0; row < aSize; ++row)
            {
                if (aGrid[column][row] == aEmptyMark)
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns if the specified mark has won by searching the
     * 2D array for three in a row horizontally, vertically or
     * diagonally
     *
     * @param aGrid char[][]: the 2D char array that represents a game board
     * @param aMark char: the char to check for
     * @return boolean: if three in a row has been found
     */
    private static boolean isVictorious(char[][] aGrid, final char aMark)
    {
        // [row][col]
        // [0][0] [0][1] [0][2]
        // [1][0] [1][1] [1][2]
        // [2][0] [2][1] [2][2]

        // TODO: Checks can be converted to for loops

        // Horizontal checks
        if (aGrid[0][0] == aMark && aGrid[0][1] == aMark && aGrid[0][2] == aMark ||
                aGrid[1][0] == aMark && aGrid[1][1] == aMark && aGrid[1][2] == aMark ||
                aGrid[2][0] == aMark && aGrid[2][1] == aMark && aGrid[2][2] == aMark)
        {
            return true;
        }

        // Vertical checks
        if (aGrid[0][0] == aMark && aGrid[1][0] == aMark && aGrid[2][0] == aMark ||
                aGrid[0][1] == aMark && aGrid[1][1] == aMark && aGrid[2][1] == aMark ||
                aGrid[0][2] == aMark && aGrid[1][2] == aMark && aGrid[2][2] == aMark)
        {
            return true;
        }

        // Diagonal checks
        if (aGrid[0][0] == aMark && aGrid[1][1] == aMark && aGrid[2][2] == aMark ||
                aGrid[2][0] == aMark && aGrid[1][1] == aMark && aGrid[0][2] == aMark)
        {
            return true;
        }

        return false;
    }

    /**
     * Returns the absolute difference between the count of aPlayerX
     * chars and aPlayerO chars in the 2D char array
     *
     * @param aGrid char[][]: the 2D char array that represents a game board
     * @param aSize int: size of the 2D arrays rows and columns
     * @param aPlayerX char: the char that represents one player
     * @param aPlayerO char: the char that represents another player
     * @return int: the absolute difference of X and O char counts
     */
    private static int getDifference(char[][] aGrid, final int aSize, final char aPlayerX, final char aPlayerO)
    {
        int xCounter = 0;
        int oCounter = 0;

        for (int column = 0; column < aSize; ++column)
        {
            for (int row = 0; row < aSize; ++row)
            {
                if (aGrid[column][row] == aPlayerX)
                {
                    ++xCounter;
                }
                else if (aGrid[column][row] == aPlayerO)
                {
                    ++oCounter;
                }
            }
        }

        return Math.abs(xCounter - oCounter);
    }
}

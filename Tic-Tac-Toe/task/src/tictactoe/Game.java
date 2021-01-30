package tictactoe;

import java.util.Scanner;

/**
 * This class represents a Tic-Tac-Toe game
 *
 * @author Zaid Neurothrone
 */
public class Game
{
    // region Fields

    private final Scanner myScanner;
    private final int mySize;
    private final char[][] myGrid;
    private final char myPlayerX;
    private final char myPlayerO;

    // endregion


    // region Constructors

    /**
     * Un-parameterized constructor
     */
    public Game()
    {
        myScanner = new Scanner(System.in);
        myPlayerX = 'X';
        myPlayerO = 'O';
        mySize = 3;
        myGrid = new char[mySize][mySize];
        initializeGrid();
    }

    // endregion


    // region Public Methods

    /**
     * The game loop
     */
    public void play()
    {
        while (true)
        {
            printGrid();
            getCoordinates(myPlayerX);
            if (hasGameConcluded())
            {
                break;
            }

            printGrid();
            getCoordinates(myPlayerO);
            if (hasGameConcluded())
            {
                break;
            }
        }
    }

    // endregion


    // region Private Methods

    /**
     * Initializes all char values in the 2D array
     * with empty spaces
     */
    private void initializeGrid()
    {
        for (int col = 0; col < mySize; ++col)
        {
            for (int row = 0; row < mySize; ++row)
            {
                myGrid[row][col] = ' ';
            }
        }
    }

    /**
     * Prompts the user for input until valid ones are
     * retrieved, then fills the grid
     *
     * @param aPlayer char: the X or O of the respective player
     */
    private void getCoordinates(final char aPlayer)
    {
        boolean isValid = false;
        while (!isValid)
        {
            System.out.print("Enter the coordinates: ");
            int inputX;
            int inputY;

            try
            {
                inputX = myScanner.nextInt();
                inputY = myScanner.nextInt();
            }
            catch (Exception e)
            {
                System.out.println("You should enter numbers!");
                myScanner.nextLine();
                continue;
            }

            int rowPosition = inputX - 1;
            int colPosition = inputY - 1;

            if (isCoordinatesValid(rowPosition, colPosition))
            {
                if (isOccupied(rowPosition, colPosition))
                {
                    System.out.println("This cell is occupied! Choose another one!");
                }
                else
                {
                    isValid = true;
                    myGrid[rowPosition][colPosition] = aPlayer;
                }
            }
            else
            {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }

    /**
     * Returns if the coordinates provided by the user
     * are valid
     *
     * @param aX int: horizontal position in the grid
     * @param aY int: vertical position in the grid
     * @return boolean: if coordinates are valid
     */
    private boolean isCoordinatesValid(final int aX, final int aY)
    {
        return aX >= 0 && aX < mySize && aY >= 0 && aY < mySize;
    }

    private boolean isOccupied(final int aX, final int aY)
    {
        return myGrid[aX][aY] != ' ';
    }

    /**
     * Prints the formatted 2D char array to represent the
     * current state of the game board
     */
    private void printGrid()
    {
        System.out.println("---------");

        for (int row = 0; row < mySize; ++row)
        {
            System.out.print("| ");
            for (int col = 0; col < mySize; ++col)
            {
                System.out.print(myGrid[row][col] + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");
    }

    /**
     * Returns the state of the game by calling helper methods to
     * determine if there is a winner, if game is not over or if
     * something has gone wrong
     *
     * @return if game is over
     */
    private boolean hasGameConcluded()
    {
        // Impossible state
        if (isVictorious(myPlayerX) && isVictorious(myPlayerO)
                || getDifference() > 1)
        {
            System.out.println("Impossible");
            return true;
        }

        // has X won?
        if (isVictorious(myPlayerX))
        {
            printGrid();
            System.out.println("X wins");
            return true;
        }

        // has O won?
        if (isVictorious(myPlayerO))
        {
            printGrid();
            System.out.println("O wins");
            return true;
        }

        // has empty spaces?
        if (!isGameOver())
        {
            return false;
        }

        // no empty spaces left and no winners => draw
        System.out.println("Draw");
        return true;
    }

    /**
     * Returns if the game is over by checking if the 2D char array
     * no longer has any empty spaces
     *
     * @return boolean: if game is over
     */
    private boolean isGameOver()
    {
        for (int column = 0; column < mySize; ++column)
        {
            for (int row = 0; row < mySize; ++row)
            {
                if (myGrid[column][row] == ' ')
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
     * @param aMark char: the char to check for
     * @return boolean: if three in a row has been found
     */
    private boolean isVictorious(final char aMark)
    {
        // [row][col]
        // [0][0] [0][1] [0][2]
        // [1][0] [1][1] [1][2]
        // [2][0] [2][1] [2][2]

        // Horizontal checks
        for (int row = 0; row < mySize; ++row)
        {
            if (myGrid[row][0] == aMark && myGrid[row][1] == aMark && myGrid[row][2] == aMark)
            {
                return true;
            }
        }

        // Vertical checks
        for (int col = 0; col < mySize; ++col)
        {
            if (myGrid[0][col] == aMark && myGrid[1][col] == aMark && myGrid[2][col] == aMark)
            {
                return true;
            }
        }

        // Diagonal checks
        if (myGrid[0][0] == aMark && myGrid[1][1] == aMark && myGrid[2][2] == aMark ||
                myGrid[2][0] == aMark && myGrid[1][1] == aMark && myGrid[0][2] == aMark)
        {
            return true;
        }

        return false;
    }

    /**
     * Returns the absolute difference between the count of player X
     * chars and player O chars in the 2D char array
     *
     * @return int: the absolute difference of X and O char counts
     */
    private int getDifference()
    {
        int xCounter = 0;
        int oCounter = 0;

        for (int column = 0; column < mySize; ++column)
        {
            for (int row = 0; row < mySize; ++row)
            {
                if (myGrid[column][row] == myPlayerX)
                {
                    ++xCounter;
                }
                else if (myGrid[column][row] == myPlayerO)
                {
                    ++oCounter;
                }
            }
        }

        return Math.abs(xCounter - oCounter);
    }

    // endregion
}

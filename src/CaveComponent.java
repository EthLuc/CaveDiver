import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Cave component class creates the cave cells/grids and finds the escape route using recursion.
 */
public class CaveComponent extends JComponent 
{
    private static final int GRID_SIZE = 10;
    private static final int CELL_SIZE = 50;
    private CaveCell[][] grid;
    private Random random = new Random();
    
    private int xOffset, yOffset;  

    /**
     * Makes a new CaveComponent and generates a grid.
     */
    public CaveComponent() 
    {
        generateNewCave();
    }
    
    /**
     * Generates a new grid with each cell having a depth between 1 and 10.
     */
    public void generateNewCave() 
    {
        grid = new CaveCell[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                int depth = random.nextInt(10) + 1;  
                grid[row][col] = new CaveCell(row, col, depth);
            }
        }
        repaint();  
    }

    /**
     * Draws the grid.
     */
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        
        xOffset = (getWidth() - (GRID_SIZE * CELL_SIZE)) / 2;
        yOffset = (getHeight() - (GRID_SIZE * CELL_SIZE)) / 2;

      
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                grid[row][col].draw(g, xOffset, yOffset);  
            }
        }
    }

    /**
     * Finds an escape route for the diver with the depth set by the user.
     * @param depthRating the diver's depth set by the user.
     * @return true if an escape route is found, false otherwise.
     */
    public boolean findEscapeRoute(int depthRating) 
    {
        clearEscapeRoute();  

        boolean result = findRoute(0, 0, depthRating, 20);  
        repaint();  
        return result;
    }

    /**
     * Clears any cells previously marked as an escape route as normal cave cell objects if they end up in a dead end.
     */
    private void clearEscapeRoute() {
        for (int row = 0; row < GRID_SIZE; row++) 
        {
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                grid[row][col].setEscapeRoute(false);  
            }
        }
    }

    /**
     * Recursive method to find an escape route starting from the top left to the bottom right.
     * @param row the current row the diver is on in the grid.
     * @param col the current column the diver is on in the grid.
     * @param depthRating the diver's depth set by the user.
     * @param airLeft the diver's remaining air left.
     * @return true if a route is found, false otherwise.
     */
    private boolean findRoute(int row, int col, int depthRating, int airLeft) 
    {
        
        if (row == GRID_SIZE - 1 && col == GRID_SIZE - 1) 
        {
            grid[row][col].setEscapeRoute(true);  
            return true;
        }

        
        if (row < GRID_SIZE && col < GRID_SIZE && row >= 0 && col >= 0 &&
            grid[row][col].getDepth() <= depthRating && airLeft > 0 && 
            !grid[row][col].isEscapeRoute()) 
            {  

            
            grid[row][col].setEscapeRoute(true);

            
            if (findRoute(row + 1, col, depthRating, airLeft - 1) || 
                findRoute(row, col + 1, depthRating, airLeft - 1)) 
            {
                return true;
            }

            
            grid[row][col].setEscapeRoute(false);
        }
        return false;
    }
}
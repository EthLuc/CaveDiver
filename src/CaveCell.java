import java.awt.*;

/**
 * Represents a single cell in the grid and stores it location by row/column, depth,
 * and if it is a part of the escape route or not.
 */
public class CaveCell 
{
    private int row, col, depth;
    private boolean escapeRoute;

    /**
     * Constructs our cave cell object with the row, column, and depth.
     * @param row the row of the cell.
     * @param col the column of the cell.
     * @param depth the depth of the cell.
     */
    public CaveCell(int row, int col, int depth) 
    {
        this.row = row;
        this.col = col;
        this.depth = depth;
        this.escapeRoute = false;
    }
    /**
    * Returns the depth of the cell.
    * @return the depth of the cell.
    */
    public int getDepth() 
    {
        return depth;
    }
    /**
     * Sets the cell as part of the escape route.
     * @param escapeRoute true if the cell is a part of the escape route, false otherwise.
     */
    public void setEscapeRoute(boolean escapeRoute) 
    {
        this.escapeRoute = escapeRoute;
    }
    /**
     * Checks if this cell is part of the escape route.
     * @return true if the cell is a part of the escape route, false otherwise.
     */
    public boolean isEscapeRoute() 
    {
        return escapeRoute;
    }
    /**
     * Draws the cell.
     * @param g the Graphics to draw on.
     * @param xOffset the x-coord offset for centering the grid on the window.
     * @param yOffset the y-coord offset for centering the grid on the window.
     */
    public void draw(Graphics g, int xOffset, int yOffset) 
    {
      
        g.setColor(Color.WHITE);
        g.fillRect(xOffset + col * 50, yOffset + row * 50, 50, 50);

        
        if (escapeRoute) 
        {
            g.setColor(Color.RED);  
        } else 
        {
            g.setColor(new Color(0, 0, 255 - (depth * 25)));  
        }
        
       
        g.fillRect(xOffset + col * 50, yOffset + row * 50, 50, 50);

        
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(depth), xOffset + col * 50 + 5, yOffset + row * 50 + 15);
    }
}
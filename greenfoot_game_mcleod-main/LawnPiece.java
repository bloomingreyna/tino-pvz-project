    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class LawnPiece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LawnPiece extends Actor
{
    /**
     * Act - do whatever the LawnPiece wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int row;
    private int col;
    
    public LawnPiece(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public void act()
    {
        
    }
    public void setTileColor(int num) {
        if (num == 1) {
            setImage("darker_grass.png");
        }
        if (num == -1) {
            setImage("lighter_grass.png");
        }
        getImage().scale(95, 105);
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
}

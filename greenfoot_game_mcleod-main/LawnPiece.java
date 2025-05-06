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
    
    static int mouseRow = -1;
    static int mouseCol = -1;
    
    private int row;
    private int col;
    
    public LawnPiece(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public void act()
    {
        updateMousePosition();
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
    
    private void updateMousePosition() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        /*
        if (mouse != null) {
            int width = getImage().getWidth();
            int height = getImage().getHeight();
            boolean xMatch = mouse.getX() > getX() - width / 2 && mouse.getX() < getX() + width / 2;
            boolean yMatch = mouse.getY() > getY() - height / 2 && mouse.getY() < getY() + height / 2;
            if (xMatch && yMatch) {
                mouseRow = row;
                mouseCol = col;
            }
        }
        */
        
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
}

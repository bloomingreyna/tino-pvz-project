import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    
    public void act()
    {
        // Add your action code here.
    }
    public void setTileColor(int num) {
        if (num == 1) {
            setImage("darker_grass.png");
        }
        if (num == -1) {
            setImage("lighter_grass.png");
        }
        getImage().scale(95, 110);
    }
}

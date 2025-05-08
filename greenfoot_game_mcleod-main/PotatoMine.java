import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PotatoMine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PotatoMine extends Plant
{
    /**
     * Act - do whatever the PotatoMine wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int health = 99999;
    int count = 0;
    public PotatoMine (int row, int col) {
        super(row,col);
        this.sunCost = 25;
    }
    public void act()
    {
        // Add your action code here.
        
    }
}

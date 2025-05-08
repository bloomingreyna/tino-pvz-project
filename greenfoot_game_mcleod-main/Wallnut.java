import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wallnut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wallnut extends Plant
{
    /**
     * Act - do whatever the Wallnut wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Wallnut(int row, int col) {
        super(row, col);
        this.health = 4000;
        getImage().scale(150,150);
        this.health = 4000;
        this.sunCost = 50;
        this.rechargeTime = 1800;
        this.startCharge = false;
    }
    
    public void act()
    
    {
        // Add your action code here.
        
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cherrybomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cherrybomb extends Plant
{
    /**
     * Act - do whatever the Cherrybomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int health = 999999999;
    public Cherrybomb(int row, int col) {
        super(row, col);
        this.sunCost = 150;
        this.rechargeTime = 3000;
        this.startCharge = false;
    }
    
    public void act()
    {
        // Add your action code here.
        LawnPiece joe = new LawnPiece(95,105);
        
        
    }
}

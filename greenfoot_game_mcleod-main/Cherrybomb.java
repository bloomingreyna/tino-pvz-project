import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
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
    int count = 0;
    
    private GreenfootImage debugBall = new GreenfootImage("ball.png");
    final int BLAST_RADIUS = 135;
    
    public Cherrybomb(int row, int col) {
        super(row, col);
        this.sunCost = 150;
        this.rechargeTime = 3000;
        this.startCharge = false;
    }
    
    public void act()
    {
        // Add your action code here.
        if (count >= 60) {
            List<Zombie> zombsExploded = getObjectsInRange(BLAST_RADIUS, Zombie.class);
            
            for(int i = 0; i < zombsExploded.size(); i++) {
                getWorld().removeObject(zombsExploded.get(i));
            }
            getWorld().removeObject(this);
            this.tile.setPlant(null);
        }
        count++;
    }
}

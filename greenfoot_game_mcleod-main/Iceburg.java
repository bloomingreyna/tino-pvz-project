import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Iceburg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Iceburg extends Plant
{
    /**
     * Act - do whatever the Iceburg wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int health = 999999999;
    int count = 0;
    boolean startCount = false;
    public Iceburg(int row, int col) {
        super(row, col);
        this.sunCost = 75;
        this.rechargeTime = 1800;
        this.startCharge = false;
    }
    public void act()
    {
        // Add your action code here.
        count++;
        if (isTouching(Zombie.class)) {
            List<Zombie> zomb = getIntersectingObjects(Zombie.class);
            
            for (int i = 0; i < zomb.size(); i++) {
                Zombie z = zomb.get(i);
                if (z.row == row && count >= 45 && z.frozen == false) {
                    z.setFrozen(true);
                    z.freezeCount = 0;
                    Greenfoot.playSound("frozen.wav");
                    getWorld().removeObject(this);
                    this.tile.setPlant(null);
                    return;
                }
            }
        }
        
    }
}

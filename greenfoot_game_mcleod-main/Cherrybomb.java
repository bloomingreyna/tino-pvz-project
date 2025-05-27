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
    int count = 0;
    
    private GreenfootImage debugBall = new GreenfootImage("ball.png");
    
    public Cherrybomb(int row, int col) {
        super(row, col);
        this.sunCost = 150;
        this.rechargeTime = 3000;
        this.startCharge = false;
        this.health = 29848495;
    }
    
    public void act()
    {
        // Add your action code here.
        
        if (count >= 60) {
            List<Zombie> zombsExploded = getWorld().getObjects(Zombie.class);
            
            for(int i = 0; i < zombsExploded.size(); i++) {
                Zombie z = zombsExploded.get(i);
                if (Math.abs(getX() - z.getX()) <= 132 + z.getImage().getWidth() / 2 && Math.abs(getY() - z.getY()) <= 132) {
                    z.die();
                }
            }
            Greenfoot.playSound("cherrybomb.wav");
            getWorld().removeObject(this);
            this.tile.setPlant(null);
        }
        
        
        
        
        count++;
    }
}

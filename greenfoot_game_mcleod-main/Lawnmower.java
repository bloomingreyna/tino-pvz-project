import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Lawnmower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lawnmower extends Actor
{
    /**
     * Act - do whatever the Lawnmower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean go = false;
    int row;
    
    public Lawnmower(int row){
        getImage().scale(100,100);
        this.row = row;
    }
    public void act()
    {
        List<Zombie> zombies = getIntersectingObjects(Zombie.class);
        for (int i = 0; i < zombies.size(); i++) {
            Zombie z = zombies.get(i);
            if (z.row == row) {
                z.setHealth(0);
                if (!go) {
                    Greenfoot.playSound("lawnmower.wav");
                    go = true;
                }
            }
        }
        
        if (go) {
            move(3);
        }
        
        
        
        if (getX() > getWorld().getWidth() + getImage().getWidth() / 2) {
            getWorld().removeObject(this);
        }
    }
}

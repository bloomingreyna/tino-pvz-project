import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Sun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sun extends Actor
{
    /**
     * Act - do whatever the Sun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int stopY;
    static int sun = 1000;
    boolean goToSun = false;
    public Sun() {
        getImage().scale(50,50);
    }
    public void addedToWorld(World world) {
        Random rand = new Random();
        stopY = rand.nextInt(((world.getHeight() - getY()) + 1) + getY());
        
    }
    public void act()
    {
        // Maybe have something different for suns that come out of sunflowers?
        if (goToSun == false && getY() < stopY) {
            setLocation(getX(), getY() + 1);
        }
        if (Greenfoot.mouseClicked(this) && goToSun == false) {
            turnTowards(100,100);
            goToSun = true;
            sun += 25;
        }
        if (goToSun) {
            
            move(3);
            if (isTouching(SunDisplay.class)) {
                goToSun = false;
                getWorld().removeObject(this);
            }
        }
        
    }
    public int getSun() {
        return sun;
    }
}

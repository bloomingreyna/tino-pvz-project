import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
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
        if (count >= 900) {
            //change image;
            health = 100;
            if (isTouching(Zombie.class)) {
                List<Zombie> exploded = getObjectsInRange(200, Zombie.class);
                for (int i = 0; i < exploded.size(); i++) {
                    Zombie zomb = exploded.get(i);
                    getWorld().removeObject(zomb);
                }
            }
        }
    }
}

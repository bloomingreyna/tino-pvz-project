import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Peashooter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Peashooter extends Plant
{
    /**
     * Act - do whatever the Peashooter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Peashooter(int row, int col) {
        super(row, col);
        getImage().scale(50,50);
    }

    public void act()
    {
        // Add your action code here.
        List <Zombie> zombs = getWorld().getObjects(Zombie.class);
        for (int i =0 ; i < zombs.size(); i++) {
            Zombie zomb = zombs.get(i);
            if (zomb.row == row) {
                Pea pea = new Pea();
                getWorld().addObject(pea, getX(), getY());
                
            }
        }
    }
}

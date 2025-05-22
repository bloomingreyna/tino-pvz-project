import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        // Add your action code here.
        Actor zomb = getOneIntersectingObject(Zombie.class);
        if (zomb != null) {
            Zombie dude = (Zombie)zomb;
            if (dude.row == row) {
                go = true;
                dude.setHealth(0);
                dude.setCauseOfDeath(0);
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    public Iceburg(int row, int col) {
        super(row, col);
        this.sunCost = 150;
        this.rechargeTime = 3000;
        this.startCharge = false;
    }
    public void act()
    {
        // Add your action code here.
        if (isTouching(Zombie.class)) {
            Zombie zomb =(Zombie) getOneIntersectingObject(Zombie.class);
            zomb.slow();
            //do icy explosion animation here
            getWorld().removeObject(this);
        }
    }
}

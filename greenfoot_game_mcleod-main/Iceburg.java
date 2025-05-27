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
        if (isTouching(Zombie.class)) {
            Zombie zomb =(Zombie) getOneIntersectingObject(Zombie.class);
            if (zomb.row == row) {
                zomb.setFrozen(true);
                
                
                //do icy explosion animation here
                getWorld().removeObject(this);
                this.tile.setPlant(null);
            }
        }
        
    }
}

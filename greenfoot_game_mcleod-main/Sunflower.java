import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sunflower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sunflower extends Plant
{
    /**
     * Act - do whatever the Sunflower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int count = 1020;
    public Sunflower(int row, int col) {
        super(row, col);
        getImage().scale(100,100);
        this.sunCost = 50;
        this.rechargeTime = 450;
        this.startCharge = true;
    }
    public void act()
    {
        // Add your action code here.
        super.act();
        count++;
        if (count == 1380) {
            Sun sun = new Sun(true);
            getWorld().addObject(sun, getX(), getY());
            setImage("light_sunflower.png");
            getImage().scale(100,100);
        }
        if (count == 1440) {
            setImage("sunflower_plain.png");
            getImage().scale(100,100);
            count = 0;
        }
    }
}

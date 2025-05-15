import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnowPea here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnowPea extends Peashooter
{
    public SnowPea(int row, int col) {
        super(row, col);
        this.sunCost = 175;
        this.startCharge = false;
    }
    
    @Override
    protected void shoot() {
        SnowPeaBullet pea = new SnowPeaBullet();
        
        getWorld().addObject(pea, getX(), getY());
    }
}

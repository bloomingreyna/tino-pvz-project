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
        getImage().scale(75, 75);
        this.sunCost = 175;
        this.rechargeTime = 450;
        this.startCharge = true;
    }
    
    @Override
    protected void shoot() {
        SnowPeaBullet pea = new SnowPeaBullet();
        
        getWorld().addObject(pea, getX(), getY());
        wait = 0;
    }
}

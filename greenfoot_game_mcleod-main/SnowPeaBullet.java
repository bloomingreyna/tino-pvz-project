 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnowPeaBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnowPeaBullet extends Pea
{
    public SnowPeaBullet() {
        getImage().scale(30, 30);
    }
    
    protected void onHit(Zombie z) {
        super.onHit(z);
        z.slow();
    }
}

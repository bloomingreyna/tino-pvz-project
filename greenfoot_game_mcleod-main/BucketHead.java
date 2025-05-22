import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BucketHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BucketHead extends Zombie
{
    /**
     * Act - do whatever the BucketHead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BucketHead(int row) {
        super(row);
        this.health = 1300;    
    }
    public void act()
    {
        super.act();
        if(health<=200){
            GreenfootImage damaged = new GreenfootImage("ezgif-89690f8ebfb687.png");
            damaged.scale(100, 100);
            setImage(damaged);
        }
        // Add your action code here.
    }
}

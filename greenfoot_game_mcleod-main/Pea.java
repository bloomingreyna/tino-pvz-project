import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pea here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pea extends Projecticle
{
    /**
     * Act - do whatever the Pea wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 5;
    int damage = 20;
    public Pea() {
        getImage().scale(40,40);
    }
    public void act()
    {
        // Add your action code here.
        move(speed);
        if (isTouching(Zombie.class)) {
            Zombie zomb = (Zombie) getOneIntersectingObject(Zombie.class);
            onHit(zomb);
        }
    }
    
    protected void onHit(Zombie z) {
        Greenfoot.playSound("splat.wav");
        z.setHealth(z.getHealth() - damage);
        getWorld().removeObject(this);
    }
}

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
    public void act()
    {
        // Add your action code here.
        move(5);
        if (isTouching(Zombie.class)) {
            Zombie zomb = (Zombie) getOneIntersectingObject(Zombie.class);
            zomb.setHealth(zomb.getHealth() - 20);
            getWorld().removeObject(this);
        }
    }
}

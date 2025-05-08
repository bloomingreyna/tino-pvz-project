import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class PotatoMine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PotatoMine extends Plant
{
    /**
     * Act - do whatever the PotatoMine wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int health = 99999;
    int count = 0;
    int count2 = 0;
    public PotatoMine (int row, int col) {
        super(row,col);
        this.sunCost = 25;
        this.rechargeTime = 1800;
        this.startCharge = false;
    }
    public void act()
    {
        // Add your action code here.
        count++;
        if (count < 900) {
            setImage("PotatoMineUnarmed.png");
            getImage().scale(50,50);
        }
        if (count >= 900) {
            //change image;
            
            count2++;
            if (count2 == 100) {
                setImage("PotatoMineRed.png");
                getImage().scale(50,50);
            }
            if (count2 == 200) {
                setImage("PotatoMineWhite.png");
                getImage().scale(50,50);
                count2 = 0;
            }
            health = 100;
            if (isTouching(Zombie.class)) {
                List<Zombie> exploded = getObjectsInRange(200, Zombie.class);
                for (int i = 0; i < exploded.size(); i++) {
                    Zombie zomb = exploded.get(i);
                    getWorld().removeObject(zomb);
                }
            }
        }
    }
}

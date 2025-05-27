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
    int count2 = 99;
    public PotatoMine (int row, int col) {
        super(row,col);
        this.sunCost = 25;
        this.rechargeTime = 1800;
        this.startCharge = false;
        setImage("PotatoMineRed.png");
    }
    public void act()
    {
        // Add your action code here.
        count++;
        if (count < 600) {
            setImage("PotatoMineUnarmed.png");
            getImage().scale(50,50);
        }
        if (count >= 600) {
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
            
            Actor zombie = getOneIntersectingObject(Zombie.class);
            if (zombie != null && Math.abs(getX() - zombie.getX()) <= 40) {
                Zombie z = (Zombie) zombie;
                if (z.row == row);
                List<Zombie> exploded = getObjectsInRange(40, Zombie.class);
                for (int i = 0; i < exploded.size(); i++) {
                    Zombie zomb = exploded.get(i);
                    if (zomb.row == row) {
                        zomb.die();
                        //make some exploding ainmation here
                        
                    }
                
                }
                Greenfoot.playSound("cherrybomb.wav");
                getWorld().removeObject(this);
                this.tile.setPlant(null);
            }
        }
    }
}

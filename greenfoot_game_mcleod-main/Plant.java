
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plant extends Actor
{
    /**
     * Act - do whatever the Plant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int health = 300;
    int row;
    int col;
    
    protected int sunCost;
    protected double rechargeTime;
    
    public Plant(int row, int col) {
        this.row = row;
        this.col = col;
        getImage().scale(100,100);
    }
    
    public void act()
    {
        
    }
    
    public void setHealth(int health){
        this.health = health;
        if (this.health <= 0) {
            getWorld().removeObject(this);
        }
    }
    
    public int getHealth(){
        return health;
    }
    

}

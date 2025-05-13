import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ConeHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConeHead extends Zombie
{
    /**
     * Act - do whatever the ConeHead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    public ConeHead(int row, int col) {
        super(row,col);
        this.health = 640;    
    }
    public void act(){
        super.act();
        if(health<=200){
            GreenfootImage damaged = new GreenfootImage("ezgif-89690f8ebfb687.png");
            damaged.scale(100, 100);
            setImage(damaged);
        }
    }
}

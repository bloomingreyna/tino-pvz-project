import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SunDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SunDisplay extends Actor
{
    /**
     * Act - do whatever the SunDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SunDisplay(String suns, int fontSize, Color textColor, Color backgroundColor) {
        setImage(new GreenfootImage(suns, fontSize, textColor, backgroundColor));
    }
    public void setSuns(String suns, int fontSize, Color textColor, Color backgroundColor) {
        setImage(new GreenfootImage(suns, fontSize, textColor, backgroundColor));
    }
    public void act()
    {
        // Add your action code here.
    }
}

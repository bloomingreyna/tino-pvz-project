import greenfoot.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SeedSlot extends Actor {
    private Constructor plant;
    private boolean selected = false;
    
    public SeedSlot(Constructor plant) {
        this.plant = plant;
        Color seedColor = new Color(125, 66, 11);
        GreenfootImage seedBG = new GreenfootImage(70, 85);
        seedBG.setColor(seedColor);
        seedBG.fill();
        setImage(seedBG);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            MyWorld.selectedSlot = this;
        }
    }
    
    public Constructor getPlant() {
        return plant;
    }
}
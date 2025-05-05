import greenfoot.*;

public class SeedSlot extends Actor {
    public SeedSlot() {
        Color seedColor = new Color(125, 66, 11);
        GreenfootImage seedBG = new GreenfootImage(70, 85);
        seedBG.setColor(seedColor);
        seedBG.fill();
        setImage(seedBG);
    }
}
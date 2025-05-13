import greenfoot.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SeedSlot extends Actor {
    private Constructor plant;
    private Plant plantRef;
    private boolean selected = false;
    
    private int rechargeTime;
    private int currentCharge;
    
    private Color chargedColor = new Color(194, 120, 0);
    private Color unchargedColor = new Color(89, 13, 2);
    
    public SeedSlot(Constructor plant, Plant plantRef) {
        this.plant = plant;
        GreenfootImage seedBG = new GreenfootImage(70, 85);
        seedBG.setColor(chargedColor);
        seedBG.fill();
        setImage(seedBG);
        getImage().drawImage(plantRef.getImage(), 0, 7);
        this.rechargeTime = plantRef.getRechargeTime();
        this.plantRef = plantRef;
        if (plantRef.isChargedAtStart()) {
            currentCharge = rechargeTime;
        } else {
            currentCharge = 0;
        }
    }
    
    public void act() {
        currentCharge += 1;
        if (Greenfoot.mouseClicked(this) && Sun.sun >= plantRef.sunCost && currentCharge >= rechargeTime) {
            MyWorld.selectedSlot = this;
        }
        
        if (currentCharge < rechargeTime || Sun.sun < plantRef.sunCost) {
            getImage().setColor(unchargedColor);
        } else {
            getImage().setColor(chargedColor);
        }
        getImage().fill();
        getImage().drawImage(plantRef.getImage(), -7, 0);
    }
    
    public Constructor getPlant() {
        return plant;
    }
    
    public void depleteCharge() {
        currentCharge = 0;
    }
}
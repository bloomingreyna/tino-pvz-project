import greenfoot.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SeedSlot extends Actor {
    private Constructor plant;
    private Plant plantRef;
    private boolean selected = false;
    
    private double rechargeTime;
    private double currentCharge;
    
    private Color chargedColor = new Color(194, 120, 0);
    private Color unchargedColor = new Color(89, 13, 2);
    private Color selectedColor = new Color(124, 54, 0);
    private Color rechargeColor = new Color(255, 255, 255, 50);
    
    private GreenfootImage rechargeVisual = new GreenfootImage(70, 1);
    
    public SeedSlot(Constructor plant, Plant plantRef) {
        this.plant = plant;
        GreenfootImage seedBG = new GreenfootImage(70, 85);
        seedBG.setColor(chargedColor);
        seedBG.fill();
        setImage(seedBG);
        getImage().drawImage(plantRef.getImage(), 0, 7);
        this.rechargeTime = plantRef.getRechargeTime();
        this.plantRef = plantRef;
        
        rechargeVisual.setColor(rechargeColor);
        rechargeVisual.fill();
        
        if (plantRef.isChargedAtStart()) {
            currentCharge = rechargeTime;
        } else {
            currentCharge = 1;
        }
    }
    
    public void act() {
        currentCharge += 1;
        if (Greenfoot.mouseClicked(this)) {
            if (MyWorld.selectedSlot == this) {
                MyWorld.selectedSlot = null;
                Greenfoot.playSound("buttonclick.wav");
                return;
            }
            
            if (Sun.sun >= plantRef.sunCost && currentCharge >= rechargeTime) {
                MyWorld.selectedSlot = this;
                Greenfoot.playSound("seedlift.wav");
            } else {
                Greenfoot.playSound("buzzer.wav");
            }
        }
        
        if (currentCharge < rechargeTime || Sun.sun < plantRef.sunCost) {
            getImage().setColor(unchargedColor);
        } else if (MyWorld.selectedSlot == this) {
            getImage().setColor(selectedColor);
        } else {
            getImage().setColor(chargedColor);
        }
        getImage().fill();
        getImage().drawImage(plantRef.getImage(), 0, 7);
        
        rechargeVisual.scale(70, (int) Math.ceil(85.0 * (currentCharge / rechargeTime)));
        getImage().drawImage(rechargeVisual, 0, 85 - rechargeVisual.getHeight());
        getWorld().showText(Integer.toString(plantRef.sunCost), getX() + 20, getY() + 30);
    }
    
    public Constructor getPlant() {
        return plant;
    }
    
    public void depleteCharge() {
        currentCharge = 1;
    }
}
import greenfoot.*;

public class Shovel extends Actor {
    boolean active = false;
    boolean fill = false;
    
    Color selectedColor = new Color(0, 0, 0, 50);
    Color unselectedColor = new Color(0, 0, 0, 0);
    
    
    public Shovel() {
        getImage().scale(75, 75);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            active = !active;
            MyWorld.selectedSlot = null;
            if (active) {
                Greenfoot.playSound("shovel.wav");
            } else {
                Greenfoot.playSound("buttonclick.wav");
            }
        }
        
        if (active) {
            getImage().setColor(selectedColor);
            if (!fill) {
                getImage().fill();
                fill = true;
            }
        } else {
            GreenfootImage img = new GreenfootImage("Shovel.png");
            img.scale(75, 75);
            setImage(img);
            fill = false;
        }
    }
}
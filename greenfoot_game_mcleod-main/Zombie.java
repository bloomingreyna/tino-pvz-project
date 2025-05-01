import greenfoot.*;

public class Zombie extends Actor {
    protected int speed;
    protected int health;
    
    public Zombie() {
        this.speed = -1;
    }
    
    public void act() {
        move(speed);
    }
}
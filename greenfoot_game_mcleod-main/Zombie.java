import greenfoot.*;

public class Zombie extends Actor {
    //States: 0 = moving, 1 = on top of plant, 2 = dead
    protected int state = 0;
    
    protected int speed;
    protected int moveDurationMax = 40;
    protected int stopDurationMax = 80;
    protected int moveDuration;
    protected int stopDuration;
    private boolean moving = true;
    
    protected int health;
    protected int damage;
    
    public Zombie() {
        this.speed = -1;
        this.moveDuration = moveDurationMax;
        this.stopDuration = stopDurationMax;
        this.health = 200;
        this.damage = 50;
    }
    
    public void act() {
        updateState();
        if (state == 0) {
            moveBehavior();
        } else if (state == 1) {
            onPlantBehavior();
        }
    }
    
    private void updateState() {
        if (health <= 0) {
            state = 2;
            return;
        }
        
        Actor plant = getOneIntersectingObject(Plant.class);
        if (plant != null) {
            state = 1;
        } else {
            state = 0;
        }
    }
    
    protected void moveBehavior() {
        if (moveDuration == 0) {
            moving = false;
            moveDuration = moveDurationMax;
        }
        
        if (stopDuration == 0) {
            moving = true;
            stopDuration = stopDurationMax;
        }
        
        if (moving) {
            move(speed);
            moveDuration--;
        } else {
            stopDuration--;
        }
    }
    
    protected void onPlantBehavior() {
        Actor plant = getOneIntersectingObject(Plant.class);
        Plant p = (Plant) plant;
        p.setHealth(p.getHealth() - damage);
    }
    
    protected void die() {
        
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
}
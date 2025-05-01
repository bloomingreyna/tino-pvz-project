import greenfoot.*;

public class Zombie extends Actor {
    //States: 0 = moving, 1 = on top of plant
    protected int state = 0;
    
    protected int speed;
    protected final int MOVE_DURATION = 40;
    protected final int STOP_DURATION = 80;
    protected int moveDuration;
    protected int stopDuration;
    private boolean moving = true;
    
    protected int health;
    
    public Zombie() {
        this.speed = -1;
        this.moveDuration = MOVE_DURATION;
        this.stopDuration = STOP_DURATION;
        this.health = 200;
    }
    
    public void act() {
        updateState();
        if (state == 0) {
            moveBehavior();
        } else {
            onPlantBehavior();
        }
    }
    
    private void updateState() {
        
    }
    
    protected void moveBehavior() {
        if (moveDuration == 0) {
            moving = false;
            moveDuration = MOVE_DURATION;
        }
                            
        if (stopDuration == 0) {
            moving = true;
            stopDuration = STOP_DURATION;
        }
        
        if (moving) {
            move(speed);
            moveDuration--;
        } else {
            stopDuration--;
        }
    }
    
    protected void onPlantBehavior() {
        
    }
}
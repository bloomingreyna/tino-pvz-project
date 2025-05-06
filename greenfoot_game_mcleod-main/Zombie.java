import greenfoot.*;

public class Zombie extends Actor {
    //States: 0 = moving, 1 = on top of plant, 2 = dead
    protected int state = 0;
    //Death causes: 0 = normal, 1 = explode, 2 = disappear
    private int causeOfDeath = 0;
    
    protected int speed;
    protected int moveDurationMax = 40;
    protected int stopDurationMax = 80;
    protected int moveDuration;
    protected int stopDuration;
    private boolean moving = true;
    
    protected int eatDurationMax = 30;
    protected int eatDuration;
    
    protected int health = 200;
    protected int damage = 50;
    
    protected int row = -1;
    protected int col = -1;
    
    public Zombie(int row, int col) {
        this.row = row;
        this.col = col;
        this.speed = -1;
        this.moveDuration = moveDurationMax;
        this.stopDuration = stopDurationMax;
        this.eatDuration = eatDurationMax;
        getImage().scale(100,100);
    }
    
    public void act() {
        updateState();
        if (state == 0) {
            moveBehavior();
            eatDuration = eatDurationMax;
        } else if (state == 1) {
            onPlantBehavior();
            moving = true;
            moveDuration = moveDurationMax;
            stopDuration = stopDurationMax;
        } else if (state == 2) {
            die();
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
        eatDuration--;
        if (eatDuration == 0) {
            eatDuration = eatDurationMax;
            Actor plant = getOneIntersectingObject(Plant.class);
            Plant p = (Plant) plant;
            p.setHealth(p.getHealth() - damage);
        }
    }
    
    protected void die() {
        if (causeOfDeath == 0) {
            getWorld().removeObject(this);
        }
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setCauseOfDeath(int causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }
}
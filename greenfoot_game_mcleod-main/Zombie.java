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
    
    private GreenfootImage debugBall = new GreenfootImage("ball.png");
    private int sensorX;
    private int sensorY;
    
    public Zombie(int row, int col) {
        this.speed = -1;
        this.moveDuration = moveDurationMax;
        this.stopDuration = stopDurationMax;
        this.eatDuration = eatDurationMax;
        this.row = row;
        this.col = col;
        getImage().scale(100,100);
        
        int x = getImage().getWidth() / 2 + 15;
        int y = getImage().getHeight() / 2 - 10;
        sensorX = x - getImage().getWidth() / 2;
        sensorY = y - getImage().getWidth() / 2;
        getImage().drawImage(debugBall, x, y);
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
        
        if (checkForPlant()) {
            state = 1;
        } else {
            state = 0;
        }
    }
    
    private boolean checkForPlant() {
        Actor l = getOneObjectAtOffset(sensorX, sensorY, LawnPiece.class);
        if (l != null) {
            LawnPiece lawnPiece = (LawnPiece) l;
            if (lawnPiece.getPlant() != null) {
                return true;
            }
        }
        return false;
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
            if (p.getHealth() <= 0) {
                Actor l = getOneObjectAtOffset(sensorX, sensorY, LawnPiece.class);
                if (l != null) {
                    LawnPiece lawnPiece = (LawnPiece) l;
                    lawnPiece.setPlant(null);
                }
            }
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
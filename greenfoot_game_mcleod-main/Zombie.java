  import greenfoot.*;

public class Zombie extends Actor {
    //States: 0 = moving, 1 = on top of plant, 2 = dead
    protected int state = 0;
    //Death causes: 0 = normal, 1 = explode, 2 = disappear
    private int causeOfDeath = 0;
    
    protected String type = "Browncoat";
    
    protected int speed = -1;
    protected int moveDurationMax = 40 + Greenfoot.getRandomNumber(8);
    protected int stopDurationMax = 80 + Greenfoot.getRandomNumber(8);
    protected int moveDuration;
    protected int stopDuration;
    private boolean moving = true;
    
    private int slowTimeMax = 600;
    int slowTime = 0;
    
    protected int eatDurationMax = 60; //30
    protected int eatDuration;
    protected boolean playEatSound = true;
    
    private int groanMax = 300;
    private int groan = groanMax;
    
    protected int health = 200;
    protected int damage = 50;
    
    protected int row = -1;
    protected int col = -1;
    
    protected boolean frozen = false;
    
    private int sensorX;
    private int sensorY;
    
    protected GreenfootImage frozenImage;
    protected GreenfootImage currentImage;
    
    private int walkStart = Greenfoot.getRandomNumber(75);
    private boolean active = false;
    int freezeCount = 0;
    public Zombie(int row) {
        this.moveDuration = moveDurationMax;
        this.stopDuration = stopDurationMax;
        this.eatDuration = eatDurationMax;
        this.row = row;
        getImage().scale(100,100);
        currentImage = getImage();
        frozenImage = new GreenfootImage("FrozenZombie.png");
        frozenImage.scale(100, 100);
        
        int x = getImage().getWidth() / 2 + 15;
        int y = getImage().getHeight() / 2 - 10;
        sensorX = x - getImage().getWidth() / 2;
        sensorY = y - getImage().getWidth() / 2;
    }
    
    public void act() {
        
        walkStart--;
        if (walkStart <= 0) {
            active = true;
            
            groan--;
            if (groan == 0) {
                groan = groanMax;
                String groanType = Integer.toString(Greenfoot.getRandomNumber(6) + 1);
                Greenfoot.playSound("groan" + groanType + ".wav");
            }
            
            updateState();
            if (state == 0) {
                moveBehavior();
                eatDuration = eatDurationMax;
            } else if (state == 1 && !frozen) {
                onPlantBehavior();
                moving = true;
                moveDuration = moveDurationMax;
                stopDuration = stopDurationMax;
            } else if (state == 2) {
                die();
            }
        }
        if (this.frozen) {
            freeze();
        }
        
        if (slowTime > 0 || frozen) {
            setImage(frozenImage);
        } else {
            setImage(currentImage);
        }
    }
    
    private void updateState() {
        slowTime--;
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
        if (moveDuration <= 0) {
            moving = false;
            moveDuration = moveDurationMax;
        }
                            
        if (stopDuration <= 0) {
            moving = true;
            stopDuration = stopDurationMax * (slowTime > 0 ? 2 : 1);
        }
        
        if (moving && !frozen) {
            move(speed);
            if (slowTime > 0) {
                moveDuration -= 2;
            } else {
                moveDuration--;
            }
        } else if (!frozen) {
            stopDuration--;
        }
    }
    
    protected void onPlantBehavior() {
        eatDuration--;
        if (slowTime > 0) {
            eatDuration--;
        } else {
            eatDuration -= 2;
        }
        if (eatDuration == 0) {
            eatDuration = eatDurationMax;
            if (playEatSound) {
                Greenfoot.playSound("chompsoft.wav");
                playEatSound = false;
            } else {
                playEatSound = true;
            }
            LawnPiece l = (LawnPiece) getOneObjectAtOffset(sensorX, sensorY, LawnPiece.class);
            Plant p = l.getPlant();
            p.setHealth(p.getHealth() - damage);
            if (p.getHealth() <= 0) {
                l.setPlant(null);
                Greenfoot.playSound("gulp.wav");
            }
        }
    }
    
    protected void die() {
        if (causeOfDeath == 0) {
            getWorld().removeObject(this);
            Greenfoot.playSound("zombie_falling_1.wav");
        }
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setFrozen(boolean f) {
        this.frozen = f;
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
    
    public void slow() {
        slowTime = slowTimeMax;
    }
    public void freeze() {
        setImage(frozenImage);
        freezeCount++;
        if (freezeCount >= 600) {
            frozen = false;
            setImage(currentImage);
            freezeCount = 0;
            slow();
        }
    }
    public boolean isActive() {
        return active;
    }
    
    public String getType() {
        return type;
    }
}
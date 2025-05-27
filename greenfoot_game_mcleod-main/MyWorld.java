import greenfoot.*;
import java.util.Random;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

public class MyWorld extends World {
    public static int mouseRow = -1;
    public static int mouseCol = -1;
    public static SeedSlot selectedSlot = null;
    public boolean start = false;
    
    int gracePeriod = 1560;
    int waveMaxGap = 1200;
    int waveGap = 0;
    int maxZombieIncreaseGap = 4;
    int zombieIncreaseGap = maxZombieIncreaseGap;
    int currentWave = 0;
    int amountOfZombiesToSpawn = 1;
    boolean fastForward = false;
    
    public static int[] lawnmowerSpawnArr = {0, 0, 0, 0, 0};
    
    SunDisplay currentDisplay = null;
    
    GreenfootSound music = new GreenfootSound("Grasswalk (In-Game).mp3");
    GreenfootSound mainMenuTrack = new GreenfootSound("Plants vs Zombies Soundtrack - Main Menu.mp3");
    
    Shovel shovel = new Shovel();

    TitleScreen title = new TitleScreen();
    GameOverScreen gameOverScreen = new GameOverScreen();
    private boolean gameOver = false;
    public MyWorld() {
        super(950, 600, 1, false);
        Greenfoot.setSpeed(50);
        Sun.sun = 50;
        if (start == false) {
            addObject(title,getWidth()/2,getHeight()/2);
        } 
    }
    
    int count = 0;
    
    public void act() {
        if (!start && !mainMenuTrack.isPlaying() && !gameOver) {
            mainMenuTrack.playLoop();
        }
        if (Greenfoot.isKeyDown("a") && !start) {
            start = true;
            removeObject(title);
            drawBackground();
            drawLawn();
            drawLawnmowers();
            drawSeedSlots();
            music.playLoop();
            mainMenuTrack.stop();
        }
        if (start == true && !gameOver) {
            
            updateMousePosition();
            spawnSun();
            placePlant();
            if (!gameOver) {
                spawnZombies();
            }
            
            int sunAmnt = Sun.sun;
            if (currentDisplay != null) {
                removeObject(currentDisplay);
            }
            
            SunDisplay sunDisplay = new SunDisplay("Sun:\n" + sunAmnt, 25, Color.YELLOW, Color.BLACK);
            addObject(sunDisplay, 35,35);
            currentDisplay = sunDisplay;
            checkGameOver();
        }
        
        if (Greenfoot.isKeyDown("a") && gameOver) {
            reset();
        }
    }
    
    private void reset() {
        gameOver = false;
        start = true;
        
        gracePeriod = 1560;
        waveMaxGap = 1200;
        waveGap = 0;
        maxZombieIncreaseGap = 4;
        zombieIncreaseGap = maxZombieIncreaseGap;
        currentWave = 0;
        amountOfZombiesToSpawn = 1;
        
        drawBackground();
        drawLawn();
        drawLawnmowers();
        drawSeedSlots();
        music.playLoop();
        Sun.sun = 50;
        
        removeObject(gameOverScreen);
        showText("", 640, 580);
    }
    
    private void checkGameOver(){
        List<Zombie> zombies = getObjects(Zombie.class);
        for(int i = 0; i<zombies.size(); i++){
            Zombie zombie = zombies.get(i);
            if(zombie.getX() <= 0){
                gameOver = true;
                List<SeedSlot> seedSlots = getObjects(SeedSlot.class);
                for (int j = 0; j < seedSlots.size(); j++) {
                    SeedSlot slot = seedSlots.get(i);
                    showText("", 125 + j * (slot.getImage().getWidth() + 3), slot.getY() + 30);
                }
                removeObjects(getObjects(null));
                addObject(gameOverScreen, getWidth() / 2, getHeight() / 2);
                showText("Press A to restart", 640, 580);
                Greenfoot.playSound("scream.wav");
                music.stop();
                start = false;
                return;
            }
        }

    }
    private void drawBackground() {
        int tileColor = 1;
        for (int i = 0; i < 15; i++) {
            for (int x = 0; x < 15; x++) {
                BackPiece back = new BackPiece();
                back.setTileColor(tileColor);
                int tileWidth = back.getImage().getWidth();
                int tileHeight = back.getImage().getHeight();
                tileColor = tileColor * -1;
                addObject(back, (x * tileWidth), ((i * tileHeight)));
            }
        }
    }
    
    private void spawnZombies() {
        for (int i = 0; i < lawnmowerSpawnArr.length; i++) {
            lawnmowerSpawnArr[i]--;
        }
        
        gracePeriod--;
        if (gracePeriod <= 0) {
            if (gracePeriod == 0) {
                Greenfoot.playSound("awooga.wav");
            }
            
            if (!fastForward && getObjects(Zombie.class).size() == 0) {
                fastForward = true;
                waveGap = (int) Math.ceil(waveGap * 0.5);
            }
            
            waveGap--;
            if (waveGap <= 0) {
                fastForward = false;
                waveGap = waveMaxGap;
                if (waveMaxGap > 120) {
                    waveMaxGap -= 20;
                }
                currentWave++;
                
                if (currentWave % 5 == 0) {
                    Greenfoot.playSound("hugewave.wav");
                }
                
                if (canSpawnZombies()) {
                    for (int i = 0; i < (currentWave % 5 != 0 ? amountOfZombiesToSpawn : amountOfZombiesToSpawn * 3); i++) {
                        int row = Greenfoot.getRandomNumber(5);
                        while (lawnmowerSpawnArr[row] > 0) {
                            row = Greenfoot.getRandomNumber(5);
                        }
                        int y = (row * 105) + getHeight() - 105 / 2 * 9;
                        Zombie z = null;
                        
                        if ((Math.random()*100) < 10 * (1 + (currentWave*0.1)) && currentWave > 10) {
                            z = new BucketHead(row);
                            
                        } else if ((Math.random()*100) < 25 * (1 + (currentWave*0.1)) && currentWave > 5) {
                            z = new ConeHead(row);
                        } else {
                            z = new Zombie(row);
                        }
                        addObject(z, getWidth(), y);
                    }
                }
                
                if (zombieIncreaseGap <= 0) {
                    amountOfZombiesToSpawn++;
                    maxZombieIncreaseGap--;
                    zombieIncreaseGap = maxZombieIncreaseGap;
                } else {
                    zombieIncreaseGap--;
                }
            }
        }
    }
    
    private boolean canSpawnZombies() {
        int numRows = 0;
        for (int i = 0; i < lawnmowerSpawnArr.length; i++) {
            if (lawnmowerSpawnArr[i] > 0) {
                numRows++;
            }
        }
        return numRows != 5;
    }
    
    private void drawLawnmowers() {
        for (int i = 0; i < 5; i++) {
            Lawnmower l = new Lawnmower(i);
            addObject(l, 25, 150 + i * 105);
        }
    }
    
    private void placePlant() {
        if (Greenfoot.isKeyDown("z") && selectedSlot != null) {
            selectedSlot = null;
            Greenfoot.playSound("buttonclick.wav");
        }
        
        if (selectedSlot != null || shovel.active) {
            LawnPiece tile = getTileAtMouse();
            if (tile != null) {
                if (Greenfoot.mouseClicked(null) && tile.getPlant() == null && !shovel.active) {
                    try {
                        MouseInfo mouse = Greenfoot.getMouseInfo();
                        Plant p = (Plant) selectedSlot.getPlant().newInstance(mouseRow, mouseCol);
                        addObject(p, tile.getX(), tile.getY());
                        Sun.sun -= p.sunCost;
                        selectedSlot.depleteCharge();
                        tile.setPlant(p);
                        p.tile = tile;
                        Greenfoot.playSound("plant.wav");
                    } catch (InstantiationException x) {
                        x.printStackTrace();
                    } catch (IllegalAccessException x) {
                        x.printStackTrace();
                    } catch (InvocationTargetException x) {
                        x.printStackTrace();
                    }
                    selectedSlot = null;
                } else if (shovel.active && Greenfoot.mouseClicked(null)) {
                    shovel.active = false;
                    if (tile.getPlant() != null) {
                        removeObject(tile.getPlant());
                        tile.setPlant(null);
                        Greenfoot.playSound("plant.wav");
                    } else {
                        Greenfoot.playSound("buttonclick.wav");
                    }
                }
            }
        }
    }
    
    private LawnPiece getTileAtMouse() {
        List<LawnPiece> tiles = getObjects(LawnPiece.class);
        for (int i = 0; i < tiles.size(); i++) {
            LawnPiece tile = tiles.get(i);
            if (tile.getRow() == mouseRow && tile.getCol() == mouseCol) {
                return tile;
            }
        }
        return null;
    }
    
    private void updateMousePosition() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            List<LawnPiece> tiles = getObjects(LawnPiece.class);
            for (int i = 0; i < tiles.size(); i++) {
                LawnPiece tile = tiles.get(i);
                int tileWidth = tile.getImage().getWidth();
                int tileHeight = tile.getImage().getHeight();
                boolean xMatch = mouse.getX() > tile.getX() - tileWidth / 2 && mouse.getX() < tile.getX() + tileWidth / 2;
                boolean yMatch = mouse.getY() > tile.getY() - tileHeight / 2 && mouse.getY() < tile.getY() + tileHeight / 2;
                if (xMatch && yMatch) {
                    mouseRow = tile.getRow();
                    mouseCol = tile.getCol();
                    return;
                }
            }
            mouseRow = -1;
            mouseCol = -1;
        }
    }
    //spawns in sun at random point on top border every 10 seconds if fps 60
    private void spawnSun() {
        if (count == 600) {
            Random rand = new Random();
            int randX = rand.nextInt(getWidth());
            Sun sun = new Sun(false);
            addObject(sun, randX, 0);
            count = 0;
        }
        count++;
    }
    
    private void drawLawn() {
        int tileColor = 1;
        for (int i = 0; i < 5; i++) {
            for (int x = 0; x < 9; x++) {
                LawnPiece lawn = new LawnPiece(i, x);
                lawn.setTileColor(tileColor);
                int tileWidth = lawn.getImage().getWidth();
                int tileHeight = lawn.getImage().getHeight();
                tileColor = tileColor * -1;
                addObject(lawn, (x * tileWidth) + 100, ((i * tileHeight) + getHeight() - tileHeight / 2 * 9));
                
            }
        }
        
    }
    
    private void drawSeedSlots() {
        Peashooter peashooterRef = new Peashooter(-1, -1);
        Sunflower sunflowerRef = new Sunflower(-1, -1);
        Cherrybomb cbRef = new Cherrybomb(-1, -1);
        Wallnut wallnutRef = new Wallnut(-1, -1);
        PotatoMine potatoMineRef = new PotatoMine(-1, -1);
        SnowPea spRef = new SnowPea(-1, -1);
        Iceburg iceburgRef = new Iceburg(-1, -1);
        
        Class[] plantClasses = {
            Peashooter.class,
            Sunflower.class,
            Cherrybomb.class,
            Wallnut.class,
            PotatoMine.class,
            SnowPea.class,
            Iceburg.class
        };
        
        Actor[] plantRefs = {
            peashooterRef,
            sunflowerRef,
            cbRef,
            wallnutRef,
            potatoMineRef,
            spRef,
            iceburgRef
        };
        
        for (int i = 0; i < plantRefs.length; i++) {
            plantRefs[i].getImage().scale(70, 70);
        }
        
        for (int i = 0; i < plantClasses.length; i++) {
            SeedSlot seedSlot = new SeedSlot(plantClasses[i].getConstructors()[0], (Plant) plantRefs[i]);
            int slotWidth = seedSlot.getImage().getWidth();
            int slotHeight = seedSlot.getImage().getHeight();
            addObject(seedSlot, i * (slotWidth + 3) + 105, slotHeight / 2 + 5);
        }
        
        addObject(shovel, plantRefs.length * 73 + 105, 42);
    }
}
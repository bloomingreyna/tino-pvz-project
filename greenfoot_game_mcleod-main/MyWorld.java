import greenfoot.*;
import java.util.Random;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

public class MyWorld extends World {
    public static int mouseRow = -1;
    public static int mouseCol = -1;
    public static SeedSlot selectedSlot = null;
    
    int gracePeriod = 1500;
    int waveMaxGap = 1020;
    int waveGap = 0;
    int maxZombieIncreaseGap = 2;
    int zombieIncreaseGap = 2;
    int currentWave = 0;
    int amountOfZombiesToSpawn = 1;
    
    SunDisplay currentDisplay = null;
    
    public MyWorld() {
        super(950, 600, 1, false);
        
        drawLawn();
        drawLawnmowers();
        drawSeedSlots();
    }
    int count = 0;
    public void act() {
        updateMousePosition();
        spawnSun();
        placePlant();
        spawnZombies();
        Sun suns = new Sun();
        int sunAmnt = suns.getSun();
        if (currentDisplay != null) {
            removeObject(currentDisplay);
        }
        SunDisplay sunDisplay = new SunDisplay("Suns: " + sunAmnt, 25, Color.YELLOW, Color.BLACK);
        addObject(sunDisplay, 100,100);
        currentDisplay = sunDisplay;
    }
    
    private void spawnZombies() {
        gracePeriod--;
        if (gracePeriod <= 0) {
            waveGap--;
            if (waveGap <= 0) {
                waveGap = waveMaxGap;
                if (waveMaxGap > 120) {
                    waveMaxGap -= 25;
                }
                currentWave++;
                for (int i = 0; i < amountOfZombiesToSpawn; i++) {
                    int row = Greenfoot.getRandomNumber(5);
                    int y = (row * 105) + getHeight() - 105 / 2 * 9;
                    Zombie z = null;
                    if (Greenfoot.getRandomNumber(100) < 30 && currentWave > 1) {
                        z = new ConeHead(row);
                    } else {
                        z = new Zombie(row);
                    }
                    addObject(z, getWidth(), y);
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
    
    private void drawLawnmowers() {
        for (int i = 0; i < 5; i++) {
            Lawnmower l = new Lawnmower();
            addObject(l, 25, 150 + i * 105);
        }
    }
    
    private void placePlant() {
        if (selectedSlot != null) {
            LawnPiece tile = getTileAtMouse();
            if (tile != null && Greenfoot.mouseClicked(null)) {
                try {
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    Plant p = (Plant) selectedSlot.getPlant().newInstance(mouseRow, mouseCol);
                    addObject(p, tile.getX(), tile.getY());
                    Sun.sun -= p.sunCost;
                    selectedSlot.depleteCharge();
                    tile.setPlant(p);
                    p.tile = tile;
                } catch (InstantiationException x) {
                    x.printStackTrace();
                } catch (IllegalAccessException x) {
                    x.printStackTrace();
                } catch (InvocationTargetException x) {
                    x.printStackTrace();
                }
            selectedSlot = null;
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
            Sun sun = new Sun();
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
    }
}
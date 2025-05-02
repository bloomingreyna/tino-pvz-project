import greenfoot.*;
import java.util.Random;
public class MyWorld extends World {
    public MyWorld() {
        super(950, 600, 1, false);
        drawLawn();
    }
    int count = 0;
    public void act() {
        //spawns in sun at random point on top border every 10 seconds if fps 60
        
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
                LawnPiece lawn = new LawnPiece();
                lawn.setTileColor(tileColor);
                int tileWidth = lawn.getImage().getWidth();
                int tileHeight = lawn.getImage().getHeight();
                tileColor = tileColor * -1;
                addObject(lawn, (x * tileWidth) + 100, (i * tileHeight) + getHeight() - tileHeight / 2 * 9);
                
            }
        }
        
    }
}
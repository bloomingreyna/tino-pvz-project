import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {
        super(950, 600, 1, false);
        int tileColor = 1;
        for (int i = 0; i < 5; i++) {
            for (int x = 0; x < 9; x++) {
                LawnPiece lawn = new LawnPiece();
                lawn.setTileColor(tileColor);
                tileColor = tileColor * -1;
                addObject(lawn, (x * 100) + 100, (i * 100) + 100);
                
            }
        }
    }
}
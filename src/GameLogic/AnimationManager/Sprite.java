package GameLogic.AnimationManager;

/**
 * Created by talhaseker on 6.10.2017.
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {

    private static BufferedImage spriteSheet;

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File("img-src/" + file + ".png"));
        } catch (IOException e) {
            System.out.println("Could'nt find image in path: " + "img-src/" + file + ".png" );
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(String file, int xGrid, int yGrid, int TILE_SIZE) {

//        if (spriteSheet == null) {
            spriteSheet = loadSprite(file);
//        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static BufferedImage getSprite(String file) {
//        if (spriteSheet == null) {
            spriteSheet = loadSprite(file);
//        }
        return spriteSheet;
    }

}
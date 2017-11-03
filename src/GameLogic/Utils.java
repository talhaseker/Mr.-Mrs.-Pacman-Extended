package GameLogic;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by talhaseker on 16.10.2017.
 */
public class Utils {

    public static Font registeredFontWithSize(float fontSize){
        Font font;
        try {
            font = (Font.createFont(Font.TRUETYPE_FONT, new File("fonts/21_WhimsyTT.ttf")));
            font = font.deriveFont(fontSize);
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
            return font;
        } catch(IOException e){
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}

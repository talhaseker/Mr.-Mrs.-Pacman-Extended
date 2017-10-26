package gamelogic.animationmanager; /**
 * Created by talhaseker on 6.10.2017.
 */

import java.awt.image.BufferedImage;

public class SpriteFrame {

    private BufferedImage frame;
    private int duration;

    public SpriteFrame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    public BufferedImage getSpriteFrame() {
        return frame;
    }

    public void setSpriteFrame(BufferedImage frame) {
        this.frame = frame;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
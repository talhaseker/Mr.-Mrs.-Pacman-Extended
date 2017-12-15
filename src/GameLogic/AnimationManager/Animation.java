package GameLogic.AnimationManager;
/**
 * Created by talhaseker on 6.10.2017.
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {

    private int frameCount;                 // Counts ticks for change
    private int frameDelay;                 // frame delay 1-12
    public int currentFrame;                // animation's current frame
    private int animationDirection;         // animation direction (i.e counting forward or backward)
    private int totalFrames;                // total amount of frames for the animation

    private boolean stopped;                // has animation stopped

    //private List<SpriteFrame> frames = new ArrayList<SpriteFrame>();    // Arraylist of frames
    private BufferedImage[] frames;

    public Animation(BufferedImage[] frames) {
        this.frameDelay = 4;
        this.stopped = true;

        this.totalFrames = this.frames.size();

        this.frames = new BufferedImage[frames.length];
        for (int i = 0; i < frames.length; i++) {
            this.frames[i] = frames[i];
        }

//        this.frameCount = 0;

        this.currentFrame = 0;
        this.animationDirection = ;


    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.size() == 0) {
            return;
        }

        stopped = false;
    }

    public void stop() {
        if (frames.size() == 0) {
            return;
        }

        stopped = true;
    }

    public void restart() {
        if (frames.size() == 0) {
            return;
        }

        stopped = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    private void addFrame(BufferedImage frame, int duration) {
        if (duration < 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new SpriteFrame(frame, duration));
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getSpriteFrame();
    }

    public void update() {
        if (!stopped) {
            frameCount++;

            if (frameCount > frameDelay) {
                frameCount = 0;
                currentFrame += animationDirection;

                if (currentFrame > totalFrames - 1) {
                    currentFrame = 0;
                }
                else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }

    }

}
package GameLogic.AnimationManager;

import GameLogic.Enums.PacmanAnimationType;
import GameLogic.Enums.PacmanType;
import GameLogic.Enums.ShieldType;
import GameLogic.ScreenItems.Shield;

import java.awt.image.BufferedImage;

/** A different implementation of Animation (created by Talha Seker)
 *
 * Changes from animation:
 * 1. Pacman needs a slightly different implementation in its frame update logic
 * 2. Pacman doesn't need animationDirection variable, its animations wrap up like a gif
 * 3. Pacman doesn't need most of the methods in Animation class
 * 4. Pacman doesn't need to implement an arrayList since no dynamic insertion will be done,
 *    Images will in a batch each time Pacman's movement type changes.
 * 5. Images of Pacman can be loaded without SpriteFrame class, (Sprite.loadsprite will be used).
 *
 * Reasoning not to have GhostAnimation:
 *    Ghost class doesn't have a gif-like animation. Changing its image manually is enough.
 *
 * @author Ecem Ilgun
 * @version 1.9
 * @since 1.0
 * @see Animation
 */
public class PacmanAnimation{
    private int frameCount;                 // Counts ticks for change
    private int frameDelay;                 // frame delay 1-12
    public int currentFrame;                // animations current frame
    private int totalFrames;                // total amount of frames for the animation
    private PacmanType pacmanType;
    private BufferedImage[] frames;

    public PacmanAnimation(PacmanType type) {
        //Initialize PacMan with a closed mouth
        this.frameDelay = 5;
        this.frameCount = 0;
        this.currentFrame = 0;
        this.pacmanType = type;

        frames = new BufferedImage[1];

        frames[0] = Sprite.loadSprite("ImageIcons/PacMan/" + type.name().toLowerCase() + "1");

        this.totalFrames = frames.length;
    }


    public void changeAnimation(PacmanAnimationType animationtype, Shield shield) {
        String subFolder = "";

        if (shield != null) {
            subFolder = shield.getType().name() + "/";
        }
        if (animationtype != PacmanAnimationType.DOWN && animationtype != PacmanAnimationType.UP &&
                animationtype != PacmanAnimationType.LEFT && animationtype != PacmanAnimationType.RIGHT)
            return;

        String direction = animationtype.name().toLowerCase();

        //Fix for Turkish keyboard
        for (int i = 0; i < direction.length(); i++)
            if (direction.charAt(i) == 'ı')
                direction = direction.substring(0, i) + 'i' + direction.substring(i + 1);

        String pacType = pacmanType.name().toLowerCase();

        this.frames = new BufferedImage[4];
        for (int i = 0; i < 3; i++) //Images form a gif-loop
            this.frames[i] = Sprite.loadSprite("ImageIcons/PacMan/" + subFolder + pacType + (i + 2) + direction);
        this.frames[3] = Sprite.loadSprite("ImageIcons/PacMan/" + subFolder + pacType + "3" + direction);

        this.totalFrames = frames.length;
    }

   public void reset() {
       this.frameCount = 0;
        this.currentFrame = 0;
   }

    public void update() {
        frameCount++;

        if (frameCount >= frameDelay) {
            frameCount = 0;

            currentFrame++;
            if (currentFrame > totalFrames - 1) { currentFrame = 0; }
        }
    }

    public BufferedImage getImage() { return frames[currentFrame];}
}

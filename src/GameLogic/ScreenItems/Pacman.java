package GameLogic.ScreenItems;

import GameLogic.Enums.Movement;
import GameLogic.Enums.PacmanAnimationType;
import GameLogic.Enums.PacmanType;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;

/** Data representation of Pacman (player)
 * @author Ecem Ilgun
 * @version 1.8
 * @since 1.0
 * @see PacmanObject
 */
public class Pacman extends MovingObject implements Serializable{
    //Variables
    private int foodEffect;        //We can (should) switch to enum later here,
    PacmanAnimationType currentAnimation; // and here.
    double foodEffectSeconds;
    Shield shield;
    private int livesLeft = 3;
    private PacmanType pacmanType;

    //Constructor(s)

    /** Default constructor of Pacman class.
     *  Initializes a not moving Mr. Pacman object.
     */
    public Pacman (PacmanType type) {
        super();
        pacmanType = type;
        if (type == PacmanType.MRPACMAN){
            super.setImage("ImageIcons/PacMan1");
            super.changePosition(372,348); //Pacman has a default starting grid
        }else {
            super.setImage("ImageIcons/PacMan2");
            super.changePosition(316,348); //Pacman has a default starting grid
            super.curMovement = Movement.LEFT;
            super.lastMovement = Movement.LEFT;
        }

        super.setSize(28,28);
        super.setSpeed(2);
//        this.score = 0;
        this.foodEffect = 0;
        this.foodEffectSeconds = 0.0;
        this.shield = null;
        this.currentAnimation = PacmanAnimationType.STOP; //Regular eating animation enum
    }

    /** Initializes a Pacman object with its parameters
     * @param type A PacmanType enumeration of Mr & Mrs Pacman
     * @param Xpos X-axis location of the upper left corner of Pacman object
     * @param Ypos Y-axis location of the upper left corner of Pacman object
     */
    public Pacman(PacmanType type, int Xpos, int Ypos) {
        this(type);
        if (type == PacmanType.MRSPACMAN){
            super.setImage("ImageIcons/GhostScared1"); //TODO: Find an image for Mrs. Pacman
        }

        this.changePosition(Xpos,Ypos);
    }

    //Methods

    /** Manages Pacman object's state if it collides with a food object.
     * @param food The food which Pacman collided with
     */
    public void eatFood(Food food) {
//        score += food.getPoints();
        this.foodEffectSeconds = food.getSideEffectSeconds();
        int sideEffect = food.getSideEffect();

        if (sideEffect == 0) {
            //TODO: Change animation accordingly
        }
    }

    public void respawn(){
        if (pacmanType == PacmanType.MRPACMAN){
            super.setXpos(372);
            super.setYpos(348);
            super.curMovement = Movement.RIGHT;
            super.lastMovement = Movement.RIGHT;
        }else {
            super.setXpos(316);
            super.setYpos(348);
            super.curMovement = Movement.LEFT;
            super.lastMovement = Movement.LEFT;
        }
    }

    public void changeMovement(){
        lastMovement = curMovement;
        //TODO: change the movement animation here
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    public PacmanType getPacmanType() {
        return pacmanType;
    }

    public void setPacmanType(PacmanType pacmanType) {
        this.pacmanType = pacmanType;
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(pacmanType);
        stream.writeObject(currentAnimation);
        stream.writeObject(shield);
        stream.writeObject(super.curMovement);
        stream.writeObject(super.lastMovement);
        stream.writeInt(livesLeft);
        stream.writeDouble(foodEffectSeconds);
        stream.writeInt(foodEffect);
        stream.writeInt(super.speed);
        stream.writeInt(super.Xpos);
        stream.writeInt(super.Ypos);
        stream.writeInt(super.width);
        stream.writeInt(super.height);
        ImageIO.write(imageIcon, "png", stream);
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        pacmanType = (PacmanType) stream.readObject();
        currentAnimation  = (PacmanAnimationType) stream.readObject();
        shield = (Shield) stream.readObject();
        super.curMovement = (Movement) stream.readObject();
        super.lastMovement = (Movement) stream.readObject();
        livesLeft = stream.readInt();
        foodEffectSeconds = stream.readDouble();
        foodEffect = stream.readInt();
        super.speed = stream.readInt();
        super.Xpos = stream.readInt();
        super.Ypos = stream.readInt();
        super.width = stream.readInt();
        super.height = stream.readInt();
        super.imageIcon = ImageIO.read(stream);
    }

    @Override
    public String toString() {
        return "Pacman{" +
                "foodEffect=" + foodEffect +
                ", currentAnimation=" + currentAnimation +
                ", foodEffectSeconds=" + foodEffectSeconds +
                ", shield=" + shield +
                ", livesLeft=" + livesLeft +
                ", pacmanType=" + pacmanType +
                ", speed=" + speed +
                ", curMovement=" + curMovement +
                ", lastMovement=" + lastMovement +
                ", imageIcon=" + imageIcon +
                ", Xpos=" + Xpos +
                ", Ypos=" + Ypos +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
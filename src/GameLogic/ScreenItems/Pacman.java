package GameLogic.ScreenItems;

import GameLogic.Enums.Movement;
import GameLogic.Enums.PacmanAnimationType;
import GameLogic.Enums.PacmanType;

/** Data representation of Pacman (player)
 * @author Ecem Ilgun
 * @version 1.8
 * @since 1.0
 * @see PacmanObject
 */
public class Pacman extends MovingObject {
    //Variables
    private int foodEffect, score;        //We can (should) switch to enum later here,
    PacmanAnimationType currentAnimation; // and here.
    double foodEffectSeconds;
    Shield shield;
    public Movement curMovement;

    //Constructor(s)

    /** Default constructor of Pacman class.
     *  Initializes a not moving Mr. Pacman object.
     */
    public Pacman () {
        super();
        super.setImage("../img-scrImageIcons/PacMan1.gif");
        super.changePosition(100,100); //Pacman has a default starting grid
        super.setSize(50,50);

        this.score = 0;
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
        this();

        if (type == PacmanType.MRSPACMAN){
            super.setImage(""); //TODO: Find an image for Mrs. Pacman
        }

        this.changePosition(Xpos,Ypos);
    }

    //Methods

    /** Manages Pacman object's state if it collides with a food object.
     * @param food The food which Pacman collided with
     */
    public void eatFood(Food food) {
        score += food.getPoints();
        this.foodEffectSeconds = food.getSideEffectSeconds();
        int sideEffect = food.getSideEffect();

        if (sideEffect == 0) {
            //TODO: Change animation accordingly
        }
    }
}
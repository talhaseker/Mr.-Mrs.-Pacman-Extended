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
public class Pacman extends PacmanObject{
    //Variables
    private int foodEffect, score;       //We can (should) switch to enum later here,
    PacmanAnimationType currentAnimation; // and here.
    double foodEffectSeconds;
    Shield shield;

    //Constructor(s)

    /** Default constructor of Pacman class.
     *  Initializes a not moving Mr. Pacman object.
     */
    public Pacman (){
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
    public Pacman(PacmanType type, int Xpos, int Ypos){
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
    public void eatFood(Food food){
        score += food.getPoints();
        this.foodEffectSeconds = food.getSideEffectSeconds();
        int sideEffect = food.getSideEffect();

        if (sideEffect == 0) {
            //TODO: Change animation accordingly
        }
    }

    // move is called each time a arrow key is
    // pressed, pacman cannot move to 2 different
    // ways at the same time (etc. up and left is forbidden)

    /** Manages pacman objects position. It's called each time user presses an arrow button
     * @param movement Movement enumeration, indicating the direction Pacman will move
     */
    public void move(Movement movement) {
        if (movement == Movement.DOWN) {
            this.changePosition(0, 20);
        }
        else if (movement == Movement.UP) {
            this.changePosition(0, -20);
        }
        else if (movement == Movement.LEFT) {
            this.changePosition(20, 0);
        }
        else if (movement == Movement.RIGHT) {
            this.changePosition(-20, 0);
        }
    }
}
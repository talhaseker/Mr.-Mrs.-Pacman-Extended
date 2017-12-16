package GameLogic.ScreenItems;

import GameLogic.Enums.FoodType;

// import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/** Represents food items Pacman will eat
 * @author Ecem Ilgun
 * @version 1.9
 * @since 1.0
 * @see PacmanObject
 */
public class Food extends PacmanObject implements Serializable{
    //Variables
    private int points, sideEffect;
    private int sideEffectSeconds;

    //Constructors
    /** Creates a Food object depending on its position and food type
     * @param type enumeration of FoodType
     * @param Xpos x-axis of image's upper left corner
     * @param Ypos y-axis of image's upper left corner
     */
    public Food(FoodType type, Graphics g, ImageObserver imageObserver, int Xpos, int Ypos){
        super();
        super.changePosition(Xpos, Ypos);
        super.setSize(28,28);

        this.points = 50;
        if (type == FoodType.BASIC){
            super.setImage("ImageIcons/basic");
            this.sideEffectSeconds = 0;
            this.sideEffect = 0;
        }
        else if(type == FoodType.BIG) {
            super.setImage("ImageIcons/big");
            this.points = 100;
            this.sideEffectSeconds = 333;
            this.sideEffect = 1;
        }
        else if(type == FoodType.YELLOW) {
            super.setImage("ImageIcons/yellow");
            this.sideEffectSeconds = 333;
            this.sideEffect = 2;
        }
        else if(type == FoodType.GREEN) {
            super.setImage("ImageIcons/green");
            this.sideEffectSeconds = 333;
            this.sideEffect = 3;
        }

        super.draw(g, imageObserver);
    }

    //Methods

    /** Returns the points to be gained if the food it eaten
     * @return point reward of the food
     */
    public int getPoints() {
        return this.points;
    }

    /** Returns the side effect enum of that particular food
     * @return integer representation of the side effect
     */
    public int getSideEffect() {
        return this.sideEffect;
    }

    /** Returns the total seconds Pacman will be affected by the side effect if the food is eaten
     * @return A double which contains the total duration of side effect, in seconds
     */
    public int getSideEffectSeconds() {
        return this.sideEffectSeconds;
    }
}

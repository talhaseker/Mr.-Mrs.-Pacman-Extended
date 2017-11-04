package GameLogic.ScreenItems;

import GameLogic.Enums.FoodType;

import javax.swing.*;

/** Represents food items Pacman will eat
 * @author Ecem Ilgun
 * @version 1.8
 * @since 1.0
 * @see PacmanObject
 */
public class Food extends PacmanObject{
    //Variables
    private int points, sideEffect;
    private double sideEffectSeconds;

    //Constructors
    /** Creates a Food object depending on its position and food type
     * @param type enumeration of FoodType
     * @param Xpos x-axis of image's upper left corner
     * @param Ypos y-axis of image's upper left corner
     */
    public Food(FoodType type, int Xpos, int Ypos){
        super();
        super.changePosition(Xpos, Ypos);
        super.setSize(20,20);

        if (type == FoodType.BIG){
            super.setImage("ImageIcons/GhostScared1.gif"); //TODO: Sorry for putting a scared ghost, I don't have food img yet
            this.points = 50;
            this.sideEffectSeconds = 0.0;
        }
        else if(type == FoodType.YELLOW) {
            //TODO
        }
        else if(type == FoodType.GREEN) {
            //TODO
        }
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
    public double getSideEffectSeconds() {
        return this.sideEffectSeconds;
    }
}

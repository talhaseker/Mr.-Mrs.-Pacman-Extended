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
    public int getPoints() {
        return this.points;
    }

    public int getSideEffect() {
        return this.sideEffect;
    }

    public double getSideEffectSeconds() {
        return this.sideEffectSeconds;
    }
}

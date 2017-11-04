/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.ScreenItems;

// import java.awt.Point;
import GameLogic.Enums.GhostType;

import java.io.Serializable;

/** Represent ghost's data
 * @author SEVVAL EKICI
 * @version 1.8
 * @since 1.0
 * @see PacmanObject
 */
public abstract class Ghost extends MovingObject implements Serializable {
    //Properties
    private GhostType type;
    private int points;
    private boolean isAlive = false; //I THINK WE SHOULD CONTROL THIS THAT WAY - Şevval
                                     // I also think we do not have to use GhostAnimationType as enum
                                     // since it can only be alive or dead. However, PacmanAnimationType
                                     // should be used. I don't know if using different approaches in
                                     // pacman and ghost is a good design choice - Ecem

    //Constructor
	public Ghost (GhostType type) {
        super();
        super.setSize(30,40); //Just Random Values, will be changed.

	    this.type = type;
        isAlive = true;
	}
        
    //Methods

    /** This method determines the place of the ghost according to their type
     *
     */
    public void startPosition()
	{
        if (this.type == GhostType.BLINKY)
            super.changePosition(10,10);
        if(this.type == GhostType.INKY)
            super.changePosition(50,50);
        if(this.type == GhostType.PINKY)
            super.changePosition(100,100);
        if(this.type == GhostType.CLYDE)
            super.changePosition(90,80);
	}

    /**
     * @return the points that user gains when pacman eats the ghost
     */
	public int getValue() {
		return points;
	}
	
	public void chase()
	{
	    if (isAlive) return;
		    //TODO
	}
        
    public void die(){
        isAlive = false;
        }
        
    public void blueMood(){}

}
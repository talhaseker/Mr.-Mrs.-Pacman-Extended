/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangame;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author SEVVAL EKICI
 */
public abstract class Ghost extends PacmanObject implements Serializable {
        
        //Properties
        int status;
        int currentAnimation;
        int scorePoints;
        boolean isAlive = false; //I THINK WE SHOULD CONTROL THIS THAT WAY
        boolean UP = false;
        boolean DOWN = false;
        boolean RIGHT = false;
        boolean LEFT = true;
        
        //constructor	
	 public Ghost (int status) {
                this.status = status; //type of the ghost
                super.width = 30;  //Just Random Values, will be changed.
                super.height = 40; //Just Random Values, will be changed.
                super.velocityX=0.0; //Object never moves
                super.velocityY=0.0; //Object never moves
                isAlive = true;
	}
	
        
         //METHODS 
         
	//This method determines the place of the ghost according to their type
	public void startPosition()
	{
            switch (status) {
            //If its blinky
                case 1:
                    super.Xpos = 10;
                    super.Ypos = 10; //Points are just made up, they will be changed
            //If its Inky
                case 2:
                    super.Xpos = 50;
                    super.Ypos = 50;
            //If its Pinky
                case 3:
                    super.Xpos = 100;
                    super.Ypos = 100;
                default:
                    //If its clyde
                    super.Xpos = 90;
                    super.Ypos = 80;
            }
	}
		
	//The points that user gains when pacman eats the ghost
	public int getValue() {
		return scorePoints;
	}
	
	public void chase()
	{
		if (isAlive){
                //DÜŞÜNEMEDİM BİRAZ KARIŞIK Bİ YERMİŞ :/
                }
		
	}
        
        public void die(){
        isAlive = false;
        }
        
        public void blueMood(){
        
        }


	
	
	
}


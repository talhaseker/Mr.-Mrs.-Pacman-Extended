/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangame;

import java.io.Serializable;

/**
 *
 * @author SEVVAL EKICI
 */
public class WallBrick extends PacmanObject implements Serializable {
        
    public WallBrick(int X, int Y) {
		super.Xpos = X;
                super.Ypos = Y;
                super.width = 30;  //Just Random Values, will be changed.
                super.height = 40; //Just Random Values, will be changed.
                super.velocityX=0.0; //Object never moves
                super.velocityY=0.0; //Object never moves
	}

}
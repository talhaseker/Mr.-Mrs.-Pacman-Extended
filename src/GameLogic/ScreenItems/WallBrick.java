/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangame;

import GameLogic.ScreenItems.PacmanObject;

import java.io.Serializable;

/**
 * @author SEVVAL EKICI
 * @version 1.8
 * @since 1.0
 * @see PacmanObject
 */
public class WallBrick extends PacmanObject implements Serializable {
        
    public WallBrick(int X, int Y) {
		super();
		super.changePosition(30,40);
		super.setImage(""); //We need to find an image later
    }
}
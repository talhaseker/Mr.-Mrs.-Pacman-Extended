/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.ScreenItems;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * @author SEVVAL EKICI
 * @version 1.8
 * @since 1.0
 * @see PacmanObject
 */
public class WallBrick extends PacmanObject implements Serializable {
        
    public WallBrick(Graphics g, ImageObserver imageObserver, int X, int Y) {
		super();
		super.changePosition(X,Y);
		super.setImage("ImageIcons/wall"); //We need to find an image later
        super.setSize(28, 28);
		super.draw(g, imageObserver);
    }
}
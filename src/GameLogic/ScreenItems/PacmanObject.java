package GameLogic.ScreenItems;

import javax.swing.*;

/** Parent class of each drawable game object
 * @author Ecem Ilgun
 * @version 1.8
 * @since 1.0
 */
public class PacmanObject {
    //Variables
    private ImageIcon imageIcon; //This will be different for each chil
    private int Xpos, Ypos, width, height;
    private double velocityX, velocityY;

    //Constructor(s)
    public PacmanObject() {
        Xpos = 0;
        Ypos = 0;
        width = 0;
        height = 0;
        velocityX = 0.0;
        velocityY = 0.0;
        imageIcon = null;
    }

    //Methods
    public void changePosition(int Xpos, int Ypos) {
            this.Xpos = Xpos;
            this.Ypos = Ypos;
    }

    public void setSize(int width, int height) {
        if (width > 0 && height > 0){
            this.width = width;
            this.height = height;
        }
        //Else display error message
    }

    public void setVelocity(int velocityX, int velocityY) {
        if (velocityX > 0 && velocityY > 0){
            this.velocityX = velocityX;
            this.velocityY = velocityY;
        }
        //Else display error message
    }

    public void setImage(String imgSrc){
        this.imageIcon = new ImageIcon(imgSrc);
    }

    public ImageIcon getImage(){ return this.imageIcon; }
}

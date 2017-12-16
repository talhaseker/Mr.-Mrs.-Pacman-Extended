package GameLogic.ScreenItems;

import GameLogic.AnimationManager.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/** Parent class of each drawable game object
 * @author Ecem Ilgun, Talha Seker
 * @version 1.9
 * @since 1.0
 */
public class PacmanObject {
    //Variables
    protected BufferedImage imageIcon; //This will be different for each child
    protected int Xpos, Ypos, width, height;

    //Constructor(s)
    /** Default parent constructor called for any type of PacmanObject,
     *  initalizes properties of the class.
     */
    public PacmanObject() {
        Xpos = 0;
        Ypos = 0;
        width = 0;
        height = 0;
        imageIcon = null;
    }

    public int getXpos() {
        return Xpos;
    }

    public void setXpos(int xpos) {
        Xpos = xpos;
    }

    public int getYpos() {
        return Ypos;
    }

    public void setYpos(int ypos) {
        Ypos = ypos;
    }
//Methods
    /** Translates the object around the screen by Xpos and Ypos units.
     * @param Xpos x-axis translation coefficient
     * @param Ypos y-axis translation coefficient
     */
    public void changePosition(int Xpos, int Ypos) {
            this.Xpos += Xpos;
            this.Ypos += Ypos;
    }

    /** Sets the width and height of the object
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        if (width > 0 && height > 0){
            this.width = width;
            this.height = height;
        }
        //Else display error message
    }

    /** Sets the imageIcon of the object according to its parameter
     * @param imgSrc A string parameter of the relative path of image source
     */
    public void setImage(String imgSrc){
        this.imageIcon = Sprite.loadSprite(imgSrc);
    }

    public void setImage(BufferedImage img){
        this.imageIcon = img;
    }

    /** Returns the imageIcon of the object
     * @return imageIcon of the object
     */
    public BufferedImage getImage(){ return this.imageIcon; }

    public void draw(Graphics g, ImageObserver imageObserver) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(imageIcon, Xpos, Ypos, width, height, imageObserver);
    }

    @Override
    public String toString() {
        return "PacmanObject{" +
                "imageIcon=" + imageIcon +
                ", Xpos=" + Xpos +
                ", Ypos=" + Ypos +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}

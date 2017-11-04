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
        this.imageIcon = new ImageIcon(imgSrc);
    }

    /** Returns the imageIcon of the object
     * @return imageIcon of the object
     */
    public ImageIcon getImage(){ return this.imageIcon; }
}

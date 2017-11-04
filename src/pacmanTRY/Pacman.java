package pacmanTRY;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 *
 * Baﬂak ﬁevval Ekici
 * 
 */
public class Pacman{
  //Properties
  int pacmanX;
  int pacmanY;
  boolean left = false, up = false, right = true, down = false;
  
  //Constructor
  public Pacman(){
    pacmanX = 250;
    pacmanY = 250;
  }
    
  //Methods
  
  public void move() {  
    if (left){
     pacmanX = pacmanX-10;
    }
    if (right){
      pacmanX = pacmanX+10;
    } 
    if (up){
      pacmanY = pacmanY- 10;
    } 
    if (down){
      pacmanY = pacmanY + 10;
    }
      
  }

}
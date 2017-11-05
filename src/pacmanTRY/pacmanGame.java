package pacmanTRY;

import java.awt.EventQueue;
import javax.swing.JFrame;
/*
 *
 * Baﬂak ﬁevval Ekici
 * Group 2H
 */

public class pacmanGame extends JFrame{
    
     PacmanPanel pacmanGame1;
    
    public pacmanGame()
    {
        pacmanGame1 = new PacmanPanel();
        add(pacmanGame1);
        
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Pacman Game");
    }

    public static void main(String[] args) {
       new pacmanGame();
    }
    
}
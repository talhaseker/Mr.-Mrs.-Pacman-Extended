package GUI;

import GUI.UIBase.WindowCloser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class GameFrame extends JFrame {

//   GameEngine engine = new GameEngine();
    public static UIManager uiManager = new UIManager();

    public GameFrame(){
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800,600));
//        this.setPreferredSize(new Dimension(400,300));
        //this.setPreferredSize(new Dimension(200,250));
        this.setResizable(false);
        this.getContentPane().add(uiManager);
        this.pack();
        this.addWindowListener(new WindowCloser());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
}

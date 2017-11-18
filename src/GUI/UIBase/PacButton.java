package GUI.UIBase;

import GameLogic.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by talhaseker on 16.10.2017.
 */
public class PacButton extends JButton {

    public PacButton(String name, float fontSize){
        super(name);
        this.setFont(Utils.registeredFontWithSize(fontSize));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.YELLOW);
        //this.setOpaque(true);
        this.setBorderPainted(false);
    }

}

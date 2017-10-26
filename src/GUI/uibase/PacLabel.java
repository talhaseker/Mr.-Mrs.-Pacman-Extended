package GUI.uibase;

import gamelogic.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by talhaseker on 16.10.2017.
 */
public class PacLabel extends JLabel{

    public PacLabel(String label, float fontSize){
        super(label);
        this.setFont(Utils.registeredFontWithSize(fontSize));
        this.setForeground(Color.YELLOW);
    }
}

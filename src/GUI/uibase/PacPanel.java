package GUI.uibase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by talhaseker on 16.10.2017.
 */
public class PacPanel extends JPanel implements ActionListener{

    public PacPanel(boolean isBackable){
        super();
        this.setBackground(Color.BLACK);
        if (isBackable){
            PacButton backButton = new PacButton("X", 24f);
            backButton.setBackground(Color.BLACK);
            backButton.addActionListener(this);
            backButton.setToolTipText("Close Screen");
            Dimension sizeMr = backButton.getPreferredSize();
            backButton.setBounds(10, 10, sizeMr.width, sizeMr.height);
        }
    }

    public void actionPerformed(ActionEvent event) {
        //TODO: When back button clicked this method will call to switch from parent class.
    }
}

package GUI.uibase;

import GUI.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by talhaseker on 16.10.2017.
 */
public class PacPanel extends JPanel implements ActionListener{

    private String backPanelName;

    public PacPanel(boolean isBackable, String backPanelName){
        super();
        this.backPanelName = backPanelName;
        this.setBackground(Color.WHITE);
        if (isBackable){
            PacButton backButton = new PacButton("X", 24f);
            backButton.setBackground(Color.BLACK);
            backButton.addActionListener(this);
            backButton.setToolTipText("Close Screen");
            Dimension sizeMr = backButton.getPreferredSize();
            backButton.setBounds(10, 10, sizeMr.width, sizeMr.height);
            //add(backButton);
        }
    }

    public void actionPerformed(ActionEvent event) {
        GameFrame.uiManager.view(backPanelName);
    }
}

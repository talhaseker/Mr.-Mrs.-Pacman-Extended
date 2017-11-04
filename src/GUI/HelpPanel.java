package GUI;

import GUI.uibase.PacButton;
import GUI.uibase.PacLabel;
import GUI.uibase.PacPanel;
import gamelogic.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class HelpPanel extends PacPanel {

    public HelpPanel(){
        super(true, Constants.MAIN_MENU_PANEL);
        this.setLayout(new FlowLayout());
        this.setBackground(Color.MAGENTA);
        JTextArea helpText;
        JScrollPane scroll;
        PacButton backButton;
        PacLabel label = new PacLabel("INFO", 20f);
        helpText = new JTextArea("Food\n" +
                "2.3.1 Basic Food\n" +
                "This kind of food does not give any new ability to pacmans. Player has to eat all basic food to pass the next level. When a basic food is eaten, player will get ten points.\n" +
                "\n" +
                "2.3.2 Big Basic Food\n" +
                "This kind of food gives pacman opportunity to eat ghosts for five seconds and this food gives forty points.\n" +
                "2.3.3 Green Food\n" +
                "Green food is new type of food. This food provides passing through walls for five seconds and gives fifty points.\n" +
                "2.3.4 Yellow Food\n" +
                "Yellow food is also new type of food. This food provides passing through ghosts for five seconds and gives fifty points.\n"
                , 12, 30);
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        scroll = new JScrollPane(helpText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        backButton = new PacButton("X", 24f);
        backButton.setBackground(Color.BLACK);
        backButton.addActionListener(this);
        backButton.setToolTipText("Close Screen");
        Dimension sizeMr = backButton.getPreferredSize();
        backButton.setBounds(10, 10, sizeMr.width, sizeMr.height);
        this.add(label);
        this.add(scroll);
        this.add(backButton);
    }

}

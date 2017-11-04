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
public class SavedMapsListPanel extends PacPanel {

    public SavedMapsListPanel(){

        super(true, Constants.MAIN_MENU_PANEL);
        this.setLayout(new FlowLayout());
        this.setBackground(Color.MAGENTA);
        JTextArea savedMapList;
        JScrollPane scroll;
        PacButton playMap;
        PacButton mainMenu;
        PacLabel savedMaps;
        savedMapList = new JTextArea("saved map 1\n" +
                "saved map 2", 10, 30);
        savedMapList.setEditable(false);
        savedMapList.setLineWrap(true);
        scroll = new JScrollPane(savedMapList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        savedMaps = new PacLabel("SAVED MAPS" ,20f);
        playMap = new PacButton("PLAY MAP", 12f);
        mainMenu = new PacButton("MAIN MENU", 12f);
        this.add(savedMaps);
        this.add(scroll);
        this.add(playMap);
        this.add(mainMenu);
        mainMenu.addActionListener(this);
    }

}
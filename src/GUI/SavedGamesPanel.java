package GUI;

import GUI.UIBase.PacButton;
import GUI.UIBase.PacLabel;
import GUI.UIBase.PacPanel;
import GameLogic.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class SavedGamesPanel extends PacPanel {

    public SavedGamesPanel() {

        super(true, Constants.MAIN_MENU_PANEL);
        this.setLayout(new FlowLayout());
        this.setBackground(Color.MAGENTA);
        JTextArea savedGameList;
        JScrollPane scroll;
        PacButton loadGame;
        PacButton mainMenu;
        PacLabel savedGames;
        savedGameList = new JTextArea("saved game 1\n" +
                "saved game 2", 10, 30);
        savedGameList.setEditable(false);
        savedGameList.setLineWrap(true);
        scroll = new JScrollPane(savedGameList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        savedGames = new PacLabel("SAVED GAMES" ,20f);
        loadGame = new PacButton("LOAD GAME", 12f);
        mainMenu = new PacButton("MAIN MENU", 12f);
        this.add(savedGames);
        this.add(scroll);
        this.add(loadGame);
        this.add(mainMenu);
        mainMenu.addActionListener(this);
        //loadGame.addActionListener(new loadGameListener());
    }
}







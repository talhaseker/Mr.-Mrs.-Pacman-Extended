package GUI;

import DataLayer.HighScoreDatabase.HighScoreData;
import DataLayer.HighScoreDatabase.HighScoreDataManager;
import GUI.UIBase.PacButton;
import GUI.UIBase.PacLabel;
import GUI.UIBase.PacPanel;
import GameLogic.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class HighScorePanel extends PacPanel {

    public HighScorePanel(){

        super(true, Constants.MAIN_MENU_PANEL);
        //this.setLayout(new FlowLayout());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.MAGENTA);
        PacButton mainMenu;
        mainMenu = new PacButton("MAIN MENU", 12f);

        HighScoreDataManager manager = new HighScoreDataManager();
        HighScoreData[] scoreData = manager.getHighScores();
        Object[][] tableData = new Object[10][2];
        Object[] columns = {"Name", "Score"};
        for (int i=0;i<10;i++){
            tableData[i][0] = scoreData[i].getName();
            tableData[i][1] = scoreData[i].getScore();
        }

        JTable table = new JTable(tableData, columns);
        this.add(table);
        this.add(mainMenu);
        mainMenu.addActionListener(this);
    }

}

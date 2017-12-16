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
        JTextArea highScoreList;
        JScrollPane scroll;
        PacButton mainMenu;
        PacLabel highScores;
        highScoreList = new JTextArea("1)\t Aziz Osman\t\t   9760 \n" +
                "2) \t Rebellion 06\t\t   4210", 10, 30);
        highScoreList.setEditable(false);
        highScoreList.setLineWrap(true);
        highScoreList.setSize(new Dimension(10,10));
        scroll = new JScrollPane(highScoreList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0,0, 400,300);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        highScores = new PacLabel("HIGHSCORES" ,20f);
        highScores.setAlignmentX(Component.CENTER_ALIGNMENT);
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
//        this.add(highScores);
//        this.add(scroll);
        this.add(table);
        this.add(mainMenu);
        mainMenu.addActionListener(this);
    }

}

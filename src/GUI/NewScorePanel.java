package GUI;

import DataLayer.HighScoreDatabase.HighScoreDataManager;
import GUI.UIBase.PacButton;
import GUI.UIBase.PacLabel;
import GUI.UIBase.PacPanel;
import GameLogic.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewScorePanel extends JPanel implements ActionListener {

    private PacButton saveScoreButton;
    private PacButton exitButton;
    private String scoreName;
    private int score;

    public NewScorePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.PINK);
        ImageIcon pauseIcon = new ImageIcon(("img-src/icons/pause2.png"));

        //Initialize Labels
        PacLabel gameOverLabel = new PacLabel("GAME IS OVER", 24f);
        gameOverLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(pauseIcon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Initialize Buttons
        saveScoreButton = new PacButton("SAVE SCORE", 14f);
        saveScoreButton.addActionListener(this);
        saveScoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton = new PacButton("EXIT", 14f);
        exitButton.addActionListener(this);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Add Items
        this.add(gameOverLabel);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(0,25)));
        this.add(saveScoreButton);
        this.add(Box.createRigidArea(new Dimension(0,25)));
        this.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == saveScoreButton){
            scoreName = JOptionPane.showInputDialog("Enter a nickname");
            HighScoreDataManager.setHighScoreData(scoreName, score);
        }else if (event.getSource() == exitButton){
            GameFrame.uiManager.view(Constants.MAIN_MENU_PANEL);
            UIManager.gameEngine = null;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                }
            });

        }else {
            GameFrame.uiManager.view(Constants.MAIN_MENU_PANEL);
        }
    }
}

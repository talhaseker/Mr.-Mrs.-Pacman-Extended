package GUI;

import GUI.UIBase.PacButton;
import GUI.UIBase.PacLabel;
import GUI.UIBase.PacPanel;
import GameLogic.Constants;
import GameLogic.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class PausePanel extends PacPanel {

    PacButton resumeButton;
    PacButton saveGameButton;
    PacButton exitButton;

    public PausePanel() {

        super(true, Constants.GAME_PANEL);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.PINK);

        ImageIcon pauseIcon = new ImageIcon(("img-src/icons/pause2.png"));

        //Initialize Labels
        PacLabel pauseLabel = new PacLabel("PAUSED", 24f);
        pauseLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        pauseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(pauseIcon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Initialize Buttons
        resumeButton = new PacButton("RESUME", 14f);
        resumeButton.addActionListener(this);
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveGameButton = new PacButton("SAVE GAME", 14f);
        saveGameButton.addActionListener(this);
        saveGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton = new PacButton("EXIT", 14f);
        exitButton.addActionListener(this);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Add Items
        this.add(pauseLabel);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(0,25)));
        this.add(resumeButton);
        this.add(Box.createRigidArea(new Dimension(0,15)));
        this.add(saveGameButton);
        this.add(Box.createRigidArea(new Dimension(0,15)));
        this.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == resumeButton){
            UIManager.gameEngine.resumeGame();
        }else if(event.getSource() == saveGameButton){

        }else if (event.getSource() == exitButton){
            GameFrame.uiManager.view(Constants.MAIN_MENU_PANEL);
            UIManager.gameEngine = null;
        }else {
            GameFrame.uiManager.view(Constants.GAME_PANEL);
        }
    }
}


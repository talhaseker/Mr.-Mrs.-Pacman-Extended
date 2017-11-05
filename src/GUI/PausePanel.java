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
public class PausePanel extends PacPanel {

    public PausePanel() {

        super(true, Constants.GAME_PANEL);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.PINK);

        ImageIcon pauseIcon = new ImageIcon(("img-src/icons/pause2.png"));
        PacButton resumeButton;
        PacButton saveGameButton;
        PacButton exitButton;

        //Initialize Labels
        PacLabel pauseLabel = new PacLabel("PAUSED", 24f);
        pauseLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        pauseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(pauseIcon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Initialize Buttons
        resumeButton = new PacButton("RESUME", 14f);
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveGameButton = new PacButton("SAVE GAME", 14f);
        saveGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton = new PacButton("EXIT", 14f);
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
}


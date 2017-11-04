package GUI;

import GUI.uibase.PacPanel;
import gamelogic.Constants;
import gamelogic.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class PausePanel extends PacPanel {

    public PausePanel() {

        super(true, Constants.GAME_PANEL);
        ImageIcon pauseIcon = new ImageIcon(("img-src/icons/pause2.png"));
        JButton resumeButton;
        JButton saveGameButton;
        JButton exitButton;
        JLabel pauseLabel = new JLabel("PAUSE GAME");
        pauseLabel.setFont(Utils.registeredFontWithSize(20f));
        JLabel label = new JLabel(pauseIcon);

        resumeButton = new JButton("RESUME");
        resumeButton.setOpaque(false);
        resumeButton.setBackground(Color.white);
        resumeButton.setForeground(Color.black);
        resumeButton.setBorderPainted(true);
        resumeButton.setFont(Utils.registeredFontWithSize(16f));

        saveGameButton = new JButton("SAVE GAME");
        saveGameButton.setOpaque(false);
        saveGameButton.setBackground(Color.white);
        saveGameButton.setBorderPainted(true);
        saveGameButton.setForeground(Color.black);
        saveGameButton.setFont(Utils.registeredFontWithSize(16f));

        exitButton = new JButton("EXIT");
        exitButton.setOpaque(false);
        exitButton.setBackground(Color.white);
        exitButton.setBorderPainted(true);
        exitButton.setForeground(Color.black);
        exitButton.setFont(Utils.registeredFontWithSize(16f));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(pauseLabel).addComponent(label).addComponent(resumeButton).addComponent(saveGameButton).addComponent(exitButton));
        layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup().
                addComponent(pauseLabel));
        vGroup.addGroup(layout.createParallelGroup().
                addComponent(label));
        vGroup.addGroup(layout.createParallelGroup().
                addComponent(resumeButton));
        vGroup.addGroup(layout.createParallelGroup().
                addComponent(saveGameButton));
        vGroup.addGroup(layout.createParallelGroup().
                addComponent(exitButton));

        layout.setVerticalGroup(vGroup);

    }
}


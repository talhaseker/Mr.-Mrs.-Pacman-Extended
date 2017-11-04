package GUI;

import GUI.UIBase.PacButton;
import GUI.UIBase.PacLabel;
import GameLogic.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class MainMenuPanel extends JPanel{
    public MainMenuPanel(){
        super(new GridBagLayout());
        this.setBackground(Color.BLACK);

        JPanel soundPanel = new JPanel();
        soundPanel.setBackground(Color.BLACK);
        ImageIcon soundOnIcon = new ImageIcon(("img-src/icons/sound-on.png"));
        JButton soundButton = new JButton( soundOnIcon);
        soundButton.setBorderPainted(false);
        soundButton.setBackground(Color.BLACK);
        soundPanel.add(soundButton);
        soundButton.setBounds(100, 100, 48, 48);

        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(Color.BLACK);
        ImageIcon helpIcon = new ImageIcon(("img-src/icons/help.png"));
        JButton helpButton = new JButton( helpIcon);
        helpButton.setBorderPainted(false);
        helpButton.setBackground(Color.BLACK);
        helpPanel.add(helpButton);
        helpButton.setBounds(0, 0, 48, 48);

        JPanel labelsPanel = new JPanel(new BorderLayout());
        labelsPanel.setBackground(Color.BLACK);
        PacLabel mrsLabel = new PacLabel("MR. 8 MRS.",72f);
        PacLabel pacmanLabel = new PacLabel("PACMAN", 72f);
        PacLabel extLabel = new PacLabel("EXT", 72f);

        labelsPanel.add(mrsLabel, BorderLayout.PAGE_START);
        labelsPanel.add(pacmanLabel, BorderLayout.CENTER);
        labelsPanel.add(extLabel, BorderLayout.PAGE_END);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;

        this.add(labelsPanel, c);

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(Color.BLACK);

        PacButton onePlayerButton = new PacButton("ONE PLAYER",18f);
        PacButton twoPlayersButton = new PacButton("TWO PLAYERS",18f);

        PacButton createMapButton = new PacButton("CREATE MAP",18f);
        PacButton loadGameButton = new PacButton("LOAD GAME",18f);
        JPanel firstButtonsPanel = new JPanel(new BorderLayout());
        firstButtonsPanel.setBackground(Color.BLACK);
        firstButtonsPanel.add(createMapButton, BorderLayout.PAGE_START);
        firstButtonsPanel.add(loadGameButton, BorderLayout.PAGE_END);

        PacButton loadMapButton = new PacButton("LOAD MAP",18f);
        PacButton highScoreButton = new PacButton("HIGH SCORES",18f);
        JPanel secondButtonsPanel = new JPanel(new BorderLayout());
        secondButtonsPanel.setBackground(Color.BLACK);
        secondButtonsPanel.add(loadMapButton, BorderLayout.PAGE_START);
        secondButtonsPanel.add(highScoreButton, BorderLayout.PAGE_END);

        c.gridx = 0;
        c.gridy = 0;
        buttonsPanel.add(onePlayerButton, c);
        c.gridx = 2;
        c.gridy = 0;
        buttonsPanel.add(twoPlayersButton, c);
        c.gridx = 1;
        c.gridy = 1;
        buttonsPanel.add(firstButtonsPanel, c);
        c.gridx = 1;
        c.gridy = 2;
        buttonsPanel.add(secondButtonsPanel, c);

        c.gridx = 1;
        c.gridy = 2;
        this.add(buttonsPanel, c);
        c.gridx = 2;
        c.gridy = 0;
        this.add(soundPanel, c);
        c.gridx = 0;
        c.gridy = 0;
        this.add(helpPanel, c);

        //Add buttons to action listener
        helpButton.addActionListener(new helpButtonListener());
        highScoreButton.addActionListener(new highScoreListener());
        onePlayerButton.addActionListener(new onePlayerListener());
        twoPlayersButton.addActionListener(new twoPlayersListener());
        createMapButton.addActionListener(new createMapListener());
        loadGameButton.addActionListener(new loadGameListener());
        loadMapButton.addActionListener(new loadMapListener());
        //soundButton.addActionListener(new soundListener());
    }

    public class helpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame.uiManager.view(Constants.HELP_PANEL);
        }
    }
    public class highScoreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame.uiManager.view(Constants.HIGHSCORE_PANEL);
        }
    }
    public class onePlayerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame.uiManager.view(Constants.GAME_PANEL);
        }
    }
    public class twoPlayersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame.uiManager.view(Constants.GAME_PANEL);
        }
    }
    public class createMapListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame.uiManager.view(Constants.CREATE_MAP_PANEL);
        }
    }
    public class loadMapListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame.uiManager.view(Constants.SAVED_MAPS_LIST_PANEL);
        }
    }
    public class loadGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame.uiManager.view(Constants.SAVED_GAME_PANEL);
        }
    }
}

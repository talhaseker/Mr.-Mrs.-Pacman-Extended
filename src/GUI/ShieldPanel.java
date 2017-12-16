package GUI;

import GameLogic.Enums.ShieldType;
import GameLogic.ScreenItems.Shield;
import GameLogic.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class ShieldPanel extends JPanel implements ActionListener {
    ButtonGroup group;

    JRadioButton nothingButton;
    JRadioButton copperButton;
    JRadioButton silverButton;
    JRadioButton goldenButton;

    JButton okButton;

    public ShieldPanel() {
        super(new FlowLayout());
        this.setBackground(Color.MAGENTA);
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 0, 0));

        nothingButton = new JRadioButton("Don't buy anything");
        copperButton = new JRadioButton("Copper Shield");
        silverButton = new JRadioButton("Silver Shield");
        goldenButton = new JRadioButton("Golden Shield");
        nothingButton.setSelected(true);

        ImageIcon nothingIcon = new ImageIcon(("img-src/icons/cross.png"));
        ImageIcon copperIcon = new ImageIcon(("img-src/icons/copperShield.png"));
        ImageIcon silverIcon = new ImageIcon(("img-src/icons/silverShield.png"));
        ImageIcon goldenIcon = new ImageIcon(("img-src/icons/goldenShield.png"));

        JLabel label = new JLabel("                                                  ", nothingIcon, JLabel.CENTER);
        JLabel label2 = new JLabel("                                 3000 pts", copperIcon, JLabel.CENTER);
        JLabel label3 = new JLabel("                                 5000 pts", silverIcon, JLabel.CENTER);
        JLabel label4 = new JLabel("                                 7000 pts", goldenIcon, JLabel.CENTER);

        JPanel iconPanel = new JPanel(new GridLayout(0, 1));

        iconPanel.add(label);
        iconPanel.add(label2);
        iconPanel.add(label3);
        iconPanel.add(label4);

        //Group the radio buttons.
        group = new ButtonGroup();
        group.add(nothingButton);
        group.add(copperButton);
        group.add(silverButton);
        group.add(goldenButton);

        JPanel radioButtonPanel = new JPanel(new GridLayout(0, 1));
        radioButtonPanel.setBackground(Color.WHITE);
        radioButtonPanel.add(nothingButton);
        radioButtonPanel.add(copperButton);
        radioButtonPanel.add(silverButton);
        radioButtonPanel.add(goldenButton);

        nothingButton.addActionListener(this);
        copperButton.addActionListener(this);
        silverButton.addActionListener(this);
        goldenButton.addActionListener(this);

        //OK BUTTON
        okButton = new JButton("  OK  ");
        okButton.setFont(Utils.registeredFontWithSize(32f));
        okButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        okButton.setBackground(Color.magenta);
        okButton.setForeground(Color.YELLOW);
        //okButton.setOpaque(true);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.gameEngine.setNextLevel();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.setBackground(Color.MAGENTA);
        gridPanel.add(radioButtonPanel, BorderLayout.LINE_START);
        gridPanel.add(iconPanel, BorderLayout.CENTER);

        gridPanel.setSize(new Dimension(gridPanel.getWidth(), gridPanel.getHeight() + 150));

        this.add(gridPanel);
        this.add(buttonPanel, BorderLayout.PAGE_END);

    }

    //Methods
    public void enableShieldsByScore(int score) {
        int currentScore = UIManager.gameEngine.getScore();
        if (currentScore < 7000) {
            goldenButton.setEnabled(false);
            if (currentScore < 5000) {
                silverButton.setEnabled(false);
                if (currentScore < 3000) copperButton.setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton button = (JRadioButton) e.getSource();
        group.clearSelection();
        button.setSelected(true);

        if (button == nothingButton ) {
            UIManager.gameEngine.setShield(null);
        }
        else if (button == copperButton ) {
            UIManager.gameEngine.setShield(new Shield(ShieldType.COPPER));
        }
        else if (button == silverButton ) {
            UIManager.gameEngine.setShield(new Shield(ShieldType.SILVER));
        }
        else if (button == goldenButton ) {
            UIManager.gameEngine.setShield(new Shield(ShieldType.GOLD));
        }
    }

}
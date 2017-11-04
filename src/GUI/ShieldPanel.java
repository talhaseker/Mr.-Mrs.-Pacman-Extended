package GUI;

import GameLogic.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class ShieldPanel extends JPanel {
    public ShieldPanel(){
        super(new FlowLayout());
        this.setBackground(Color.MAGENTA);
        JPanel gridPanel= new JPanel(new GridLayout(0,2, 0, 0));
        JRadioButton nothingButton = new JRadioButton("Don't buy anything");
        nothingButton.setSelected(true);
        JRadioButton copperButton = new JRadioButton("Copper Shield");

        JRadioButton silverButton = new JRadioButton("Silver Shield");

        JRadioButton goldenButton = new JRadioButton("Golden Shield");
        ImageIcon nothingIcon = new ImageIcon(("img-src/icons/cross.png"));
        ImageIcon copperIcon = new ImageIcon(("img-src/icons/copperShield.png"));
        ImageIcon silverIcon = new ImageIcon(("img-src/icons/silverShield.png"));
        ImageIcon goldenIcon = new ImageIcon(("img-src/icons/goldenShield.png"));
        JLabel label = new JLabel("                                                  ", nothingIcon, JLabel.CENTER);
        JLabel label2 = new JLabel("                                 3000 pts", copperIcon, JLabel.CENTER);
        JLabel label3 = new JLabel("                                 6000 pts", silverIcon, JLabel.CENTER);
        JLabel label4 = new JLabel("                                 9000 pts", goldenIcon, JLabel.CENTER);
        JPanel iconPanel = new JPanel(new GridLayout(0, 1));
        iconPanel.add(label);
        iconPanel.add(label2);
        iconPanel.add(label3);
        iconPanel.add(label4);
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
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

        //OK BUTTON
        JButton okButton = new JButton("  OK  ");
        okButton.setFont(Utils.registeredFontWithSize(32f));
        okButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        okButton.setBackground(Color.magenta);
        okButton.setForeground(Color.YELLOW);
        //okButton.setOpaque(true);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.setBackground(Color.MAGENTA);
        gridPanel.add(radioButtonPanel, BorderLayout.LINE_START);
        gridPanel.add(iconPanel, BorderLayout.CENTER);

        gridPanel.setSize(new Dimension(gridPanel.getWidth(), gridPanel.getHeight() + 150));

        this.add(gridPanel);
        this.add(buttonPanel, BorderLayout.PAGE_END);



    }

}

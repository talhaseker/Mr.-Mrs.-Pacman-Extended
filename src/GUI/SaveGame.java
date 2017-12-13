package GUI;

import GameLogic.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveGame extends JPanel implements ActionListener {
    JButton back;
    JButton save;
    JTextField tf;
    public SaveGame(){
        super(new FlowLayout());
        this.setBackground(Color.white);
        tf = new JTextField("SavedGame", 30);
        back = new JButton("BACK");
        save = new JButton("SAVE");
        back.addActionListener(this);
        save.addActionListener(this);
        tf.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        this.add(tf);
        this.add(back);
        this.add(save);
        }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == back){
            GameFrame.uiManager.view(Constants.PAUSE_PANEL);
        }
        else if(event.getSource() == save){
            UIManager.gameEngine.saveGame(tf.getText());
            GameFrame.uiManager.view(Constants.MAIN_MENU_PANEL);
            UIManager.gameEngine = null;

        }
    }
}

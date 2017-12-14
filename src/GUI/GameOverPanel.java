package GUI;

import GUI.UIBase.PacButton;
import GUI.UIBase.PacLabel;
import GameLogic.AnimationManager.Sprite;
import GameLogic.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class GameOverPanel extends JPanel implements ActionListener {
    PacButton playAgainButton;
    public GameOverPanel(){
        super(null);
        this.setBackground(Color.BLACK);
        PacLabel gameOverLabel = new PacLabel("GAME OVER...", 72f);
        playAgainButton = new PacButton("PLAY AGAIN", 36f);
        playAgainButton.setBackground(Color.BLACK);

        this.add(gameOverLabel);
        this.add(playAgainButton);
        Dimension gameOverDim = gameOverLabel.getPreferredSize();
        gameOverLabel.setBounds(120, 80, gameOverDim.width, gameOverDim.height);
        Dimension playDim = playAgainButton.getPreferredSize();
        playAgainButton.setBounds(220, 350, playDim.width, playDim.height);
        playAgainButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == playAgainButton){
            GameFrame.uiManager.view(Constants.MAIN_MENU_PANEL);
            UIManager.gameEngine = null;

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Sprite.loadSprite("gameover/gameover"), 0, 0, null);
    }
}

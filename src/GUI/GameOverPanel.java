package GUI;

import GUI.uibase.PacButton;
import GUI.uibase.PacLabel;
import gamelogic.animationmanager.Sprite;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class GameOverPanel extends JPanel {

    public GameOverPanel(){
        super(null);
        this.setBackground(Color.BLACK);
        PacLabel gameOverLabel = new PacLabel("GAME OVER...", 72f);
        PacButton playAgainButton = new PacButton("PLAY AGAIN", 36f);
        playAgainButton.setBackground(Color.BLACK);

        this.add(gameOverLabel);
        this.add(playAgainButton);
        Dimension gameOverDim = gameOverLabel.getPreferredSize();
        gameOverLabel.setBounds(120, 80, gameOverDim.width, gameOverDim.height);
        Dimension playDim = playAgainButton.getPreferredSize();
        playAgainButton.setBounds(220, 350, playDim.width, playDim.height);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Sprite.loadSprite("gameover/gameover"), 0, 0, null);
    }
}

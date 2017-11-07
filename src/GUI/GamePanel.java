package GUI;

import GUI.UIBase.PacLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class GamePanel extends JPanel {

    public GamePanel(){
        super();
        prepareGUI();
    }

    public void drawWalls(int[][] mapTable){

    }

    public void drawFood(int[][] mapTable){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void prepareGUI(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBackground(Color.BLACK);
        PacLabel scoreLabel = new PacLabel("SCORE: 0", 24f);
        PacLabel livesLabel = new PacLabel("LIVES: ", 24f);
        bottomPanel.add(scoreLabel);
        bottomPanel.add(livesLabel);

        this.add(bottomPanel, BorderLayout.PAGE_END);
    }
}

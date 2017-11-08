package GUI;

import GUI.UIBase.PacLabel;
import GameLogic.ScreenItems.WallBrick;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class GamePanel extends JPanel {

    JPanel bottomPanel;
    JPanel mainPanel;
    JPanel foodPanel;
    JPanel movementPanel;
    PacLabel scoreLabel;
    PacLabel livesLabel;
    PacLabel getReadyLabel;
    final int width = 20, height = 3;
    final int initialX = 120, initalY = 96;
    int[][] gameMap;

    public GamePanel(int[][] gameMap){
        super();
        this.gameMap = gameMap;
//        prepareGUI();
//        drawWalls(gameMap);
//        drawFood(gameMap);
    }

    public void drawWalls(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (gameMap[i][j] == 1){
//                    System.out.println("first: " + mainPanel.getGraphics());
                    new WallBrick(mainPanel.getGraphics(), mainPanel,(initialX + j*28), (initalY + i*28));
//                    mainPanel.repaint();
                }
            }
        }
//        repaint();
    }

    public void drawFood(){

    }

    public void drawMovement(){

    }

    public void updateScore(int score){
        this.scoreLabel.setText("SCORE " + score);
    }

    public void updateLives(int lives){
        this.livesLabel.setText("LIVES " + lives);
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("repaint requesrt received");
        drawWalls();
        drawFood();
        super.paintComponent(g);
    }

    public void repaintRequest(int[][] gameMap){
        this.gameMap = gameMap;
        repaint();
    }

    public void prepareGUI(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBackground(Color.BLACK);
        scoreLabel = new PacLabel("SCORE 0", 24f);
        livesLabel = new PacLabel("LIVES 5", 24f);
        bottomPanel.add(scoreLabel);
        bottomPanel.add(livesLabel);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(800,600));
        mainPanel.setOpaque(false);

        foodPanel = new JPanel();
        foodPanel.setLayout(null);
        foodPanel.setPreferredSize(new Dimension(800,600));
        foodPanel.setOpaque(false);

        movementPanel = new JPanel();
        movementPanel.setLayout(null);
        movementPanel.setPreferredSize(new Dimension(800,600));
        movementPanel.setOpaque(false);

        getReadyLabel = new PacLabel("GET READY...", 36f);
        Dimension dimension = getReadyLabel.getPreferredSize();
        movementPanel.add(getReadyLabel);
        getReadyLabel.setLocation(400 - dimension.width/2, 300 - dimension.height);
        getReadyLabel.setSize(dimension);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(foodPanel, BorderLayout.CENTER);
        this.add(movementPanel, BorderLayout.CENTER);

//        System.out.println(mainPanel.getPreferredSize().getHeight() + " " + mainPanel.getPreferredSize().getWidth());

        this.add(bottomPanel, BorderLayout.PAGE_END);

        drawWalls();
        drawFood();
    }

    public void showGetReady(){
        getReadyLabel.setVisible(true);
    }
    public void hideGetReady(){
        getReadyLabel.setVisible(false);
    }
}

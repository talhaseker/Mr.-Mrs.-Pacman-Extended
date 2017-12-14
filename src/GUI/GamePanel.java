package GUI;

import GUI.UIBase.PacLabel;
import GameLogic.Enums.FoodType;
import GameLogic.ScreenItems.Food;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;
import GameLogic.ScreenItems.WallBrick;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class GamePanel extends JPanel {

    JPanel bottomPanel;
    JPanel mainPanel; //drawing walls blocks
    JPanel foodPanel;
    JPanel movementPanel;
    PacLabel scoreLabel;
    PacLabel livesLabel;
    PacLabel getReadyLabel;
    boolean isFirstDraw = true;
    boolean foodChanged = true;
    ArrayList<WallBrick> wallBricks = new ArrayList<WallBrick>();
    public ArrayList<Food> foods = new ArrayList<Food>();
    final int width = 20, height = 11;
    final int initialX = 120, initalY = 96;
    int[][] gameMap;
    private Pacman[] pacmans;
    private Ghost[] ghosts;

    public GamePanel(int[][] gameMap, Pacman[] pacmans, Ghost[] ghosts){
        super();
        this.gameMap = gameMap;
        this.pacmans = pacmans;
        this.ghosts = ghosts;
    }

    public void updateScore(int score){
        this.scoreLabel.setText("SCORE " + score + "    ");
    }

    public void updateLives(int lives){
        this.livesLabel.setText("LIVES " + lives);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void repaintRequest(int[][] gameMap){
        this.gameMap = gameMap;
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
                repaint(100);
//            }});
    }

    public void prepareGUI(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBackground(Color.BLACK);
        scoreLabel = new PacLabel("SCORE 0  ", 24f);
        livesLabel = new PacLabel("LIVES 5", 24f);
        bottomPanel.add(scoreLabel);
        bottomPanel.add(livesLabel);

        mainPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                if (isFirstDraw){
                    for (int i = 0; i < height; i++){
                        for (int j = 0; j < width; j++){
                            if (gameMap[i][j] == 1){
                                wallBricks.add(new WallBrick(g, mainPanel,(initialX + j*28), (initalY + i*28)));
                            }
                        }
                    }
                    isFirstDraw = false;
                }else{
                    for (WallBrick wb: wallBricks){
                        wb.draw(g, mainPanel);
                    }
                }
                super.paintComponent(g);
            }
        };
//        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(800,600));
        mainPanel.setOpaque(false);

        foodPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                if (foodChanged){
                    for (int i = 0; i < height; i++){
                        for (int j = 0; j < width; j++){
                            switch (gameMap[i][j]){
                                case 2:
                                    foods.add(new Food(FoodType.BASIC, g, foodPanel,(initialX + j*28), (initalY + i*28)));
                                    break;
                                case 3:
                                    foods.add(new Food(FoodType.BIG, g, foodPanel,(initialX + j*28), (initalY + i*28)));
                                    break;
                                case 4:
                                    foods.add(new Food(FoodType.YELLOW, g, foodPanel,(initialX + j*28), (initalY + i*28)));
                                    break;
                                case 5:
                                    foods.add(new Food(FoodType.GREEN, g, foodPanel,(initialX + j*28), (initalY + i*28)));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    foodChanged = false;
                }else{
                    for (Food f: foods){
                        f.draw(g, foodPanel);
                    }
                }
                super.paintComponent(g);
            }
        };
//        foodPanel.setLayout(null);
        foodPanel.setPreferredSize(new Dimension(800,600));
        foodPanel.setOpaque(false);

        movementPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                for (Pacman pm : pacmans)
                    pm.draw(g, this);
                for (Ghost gh : ghosts)
                    gh.draw(g, this);
            }
        };
        movementPanel.setLayout(null);
        movementPanel.setPreferredSize(new Dimension(800,600));
        movementPanel.setOpaque(true);

        getReadyLabel = new PacLabel("GET READY...", 48f);
        Dimension dimension = getReadyLabel.getPreferredSize();
        getReadyLabel.setBackground(Color.BLACK);
        movementPanel.add(getReadyLabel);
        getReadyLabel.setLocation(400 - dimension.width/2, 300 - dimension.height);
        getReadyLabel.setSize(dimension);

        mainPanel.add(foodPanel);
        foodPanel.setLocation(0,0);
        foodPanel.setSize(new Dimension(800,600));

        foodPanel.add(movementPanel);
        movementPanel.setLocation(0,0);
        movementPanel.setSize(new Dimension(800,600));

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.PAGE_END);
        repaintRequest(gameMap);

    }

    public void showGetReady(){
        getReadyLabel.setVisible(true);
    }
    public void hideGetReady(){
        getReadyLabel.setVisible(false);
    }
}

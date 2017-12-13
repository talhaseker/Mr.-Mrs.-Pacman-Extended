package GameLogic.UpdateManager;

import GUI.GamePanel;
import GameLogic.GameEngine;
import GameLogic.ScreenItems.Food;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class TimeController implements ActionListener {

    private Timer timer;
    private int count;
    private int waitCounter;

    private boolean isMultiplayer;
    private GameEngine ge;
    private Pacman[] pacmans;
    private Ghost[] ghosts;
    private GamePanel gamePanel;
    private int[][] gameMap;
    private ArrayList<Food> foods;
    private UpdateService updateService;

    public TimeController(GameEngine ge, int[][] gameMap, ArrayList<Food> foods, Pacman[] pacmans, boolean isMultiplayer, Ghost[] ghosts, GamePanel gamePanel){
        this.ge = ge;
        this.gameMap = gameMap;
        this.foods = foods;
        this.isMultiplayer = isMultiplayer;
        this.pacmans = pacmans;
        this.ghosts = ghosts;
        this.gamePanel = gamePanel;
        this.updateService = new UpdateService(ge, gameMap, foods, pacmans, isMultiplayer, ghosts, gamePanel);
        this.timer = new Timer(15, this);
    }

    // start timer
    public void startTimer() {
        timer.start();
        waitCounter = 100;
        gamePanel.showGetReady();
    }

    public void stopTimer() {
        timer.stop();
    }

    // setter for count
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (waitCounter > 0){
            waitCounter--;
            if (waitCounter == 0)
                gamePanel.hideGetReady();
            return;
        }

        updateService.updateObjects();

        count++;
    }
}

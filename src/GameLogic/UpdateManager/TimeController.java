package GameLogic.UpdateManager;

import GUI.GamePanel;
import GameLogic.Enums.ShieldType;
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
    private UpdateService updateService;

    protected boolean isMultiplayer;
    protected GameEngine ge;
    protected Pacman[] pacmans;
    protected Ghost[] ghosts;
    protected GamePanel gamePanel;
    protected int[][] gameMap;
    protected ArrayList<Food> foods;

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
        for (Pacman p:pacmans) {
            checkSideEffectsAndShields(p);
        }
        count++;
    }

    private void checkSideEffectsAndShields(Pacman p){
        if (p.getFoodEffectSeconds() > 0){
            p.setFoodEffectSeconds(p.getFoodEffectSeconds()-1);
        }else if (p.getFoodEffectSeconds() == 0){
            p.setCanEatGhost(false);
            p.setCanPassGhost(false);
            p.setCanPassWall(false);
            p.setFoodEffectSeconds(-1);
        }

        if (p.getShield() != null && p.getShield().getEffectTime() > 0){
            p.getShield().setEffectTime(p.getShield().getEffectTime()-1);
        }else if (p.getShield() != null && p.getShield().getEffectTime() == 0){
            if (p.getShield().getType() != ShieldType.GOLD)
                p.setShield(null);
            if (p.getShield() != null){ //I needed to make this check here since it tries to use a shield method (setEffectTime)
                p.getShield().setEffectTime(-1);
            }
        }
    }
}
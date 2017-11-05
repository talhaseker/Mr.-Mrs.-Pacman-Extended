package GameLogic.UpdateManager;

import GUI.GamePanel;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class TimeController implements ActionListener {

    private Timer timer;
    private int count;

    private boolean isMultiplayer;
    private ArrayList<Pacman> pacmans;
    private List<Ghost> ghosts;
    private GamePanel gamePanel;

    public TimeController(ArrayList<Pacman> pacmans, boolean isMultiplayer, List<Ghost> ghosts, GamePanel gamePanel){
        this.isMultiplayer = isMultiplayer;
        this.pacmans = pacmans;
        this.ghosts = ghosts;
        this.gamePanel = gamePanel;
        timer = new Timer(15, this);
    }

    // start timer
    public void startTimer() {
        timer.start();
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

        // do updates which also include collision handles
        UpdateService updateService = new UpdateService(pacmans, isMultiplayer, ghosts, gamePanel);
        updateService.updateObjects();

        count++;
    }
}

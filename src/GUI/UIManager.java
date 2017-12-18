package GUI;

import GameLogic.Constants;
import GameLogic.GameEngine;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class UIManager extends JPanel {

    CreateMapPanel createMapPanel;
    GameOverPanel gameOverPanel;
//    GamePanel gamePanel;
    HelpPanel helpPanel;
    HighScorePanel highScorePanel;
    MainMenuPanel mainMenuPanel;
    PausePanel pausePanel;
    SavedGamesPanel savedGamesPanel;
    SavedMapsListPanel savedMapsListPanel;
//    ShieldPanel shieldPanel;
    SaveGame saveGamePanel;
    static GameEngine gameEngine;

    public UIManager(){
        super(new CardLayout());

        createMapPanel = new CreateMapPanel();
        gameOverPanel = new GameOverPanel(0);
        helpPanel = new HelpPanel();
        highScorePanel = new HighScorePanel();
        mainMenuPanel = new MainMenuPanel();
//        pausePanel = new PausePanel();
        savedGamesPanel = new SavedGamesPanel();
        savedMapsListPanel = new SavedMapsListPanel();
//        shieldPanel = new ShieldPanel();
        saveGamePanel = new SaveGame();

        this.add(mainMenuPanel, Constants.MAIN_MENU_PANEL);
        this.add(createMapPanel, Constants.CREATE_MAP_PANEL);
        this.add(gameOverPanel, Constants.GAME_OVER_PANEL);
//        this.add(gamePanel, Constants.GAME_PANEL);
        this.add(helpPanel, Constants.HELP_PANEL);
        this.add(highScorePanel, Constants.HIGHSCORE_PANEL);
        this.add(mainMenuPanel, Constants.MAIN_MENU_PANEL);
//        this.add(pausePanel, Constants.PAUSE_PANEL);
        this.add(savedGamesPanel, Constants.SAVED_GAME_PANEL);
        this.add(savedMapsListPanel, Constants.SAVED_MAPS_LIST_PANEL);
//        this.add(shieldPanel, Constants.SHIELD_PANEL);
        this.add(saveGamePanel, Constants.SAVE_GAME_PANEL);

        viewMainMenu();
//        viewCreateMap();
    }

    public void viewHelp(){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, Constants.HELP_PANEL);
    }

    public void viewHighScores(){
        highScorePanel = new HighScorePanel();
        this.add(highScorePanel, Constants.HIGHSCORE_PANEL);
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, Constants.HIGHSCORE_PANEL);
    }

    public void viewGame(int playerNum, String name){
        CardLayout cl = (CardLayout)(this.getLayout());
        if (gameEngine == null){
            gameEngine = new GameEngine(this, playerNum, name);
        }
        cl.show(this, Constants.GAME_PANEL);
    }

    public void viewMainMenu(){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, Constants.MAIN_MENU_PANEL);
    }

    public void viewSavedGames(){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, Constants.SAVED_GAME_PANEL);
    }

    public void viewSavedMaps(){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, Constants.SAVED_MAPS_LIST_PANEL);
    }

    public void viewPause(){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, Constants.PAUSE_PANEL);
    }

//    public void viewShield(){
//        CardLayout cl = (CardLayout)(this.getLayout());
//        cl.show(this, Constants.SHIELD_PANEL);
//    }

    public void viewCreateMap(){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, Constants.CREATE_MAP_PANEL);
    }
    public void viewGameOver(int score){
        gameOverPanel = new GameOverPanel(score);
        this.add(gameOverPanel, Constants.GAME_OVER_PANEL);
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, Constants.GAME_OVER_PANEL);
    }
    /*public void viewSaveGame(){
        CardLayout c1 = (CardLayout)(this.getLayout());
        c1.show(this, Constants.SAVE_GAME_PANEL);
    }*/
    public void view(String panelName){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, panelName);
    }
    public void setSoundGetReady() {
        if (mainMenuPanel.isSound()) {
            File so = new File("pacman_beginning.wav");

            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(so));
                clip.start();

                //Thread.sleep(clip.getMicrosecondLength()/1000);
                //clip.close();
            } catch (Exception e) {
            }
        }
    }
    public void setSoundEatFood() {
        if (mainMenuPanel.isSound()) {
            File so = new File("pacman_chomp.wav");

            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(so));
                clip.start();

                //Thread.sleep(clip.getMicrosecondLength()/1000);
                //clip.close();
            } catch (Exception e) {
            }
        }
    }

}

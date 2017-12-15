package GameLogic;

import DataLayer.GameDatabase.GameData;
import DataLayer.GameDatabase.GameDataManager;
import GUI.GameFrame;
import GUI.GamePanel;
import GUI.UIManager;
import GameLogic.Enums.GhostType;
import GameLogic.Enums.PacmanType;
import GameLogic.InputManager.PacManMovementController;
import GameLogic.InputManager.PauseGameController;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;
import GameLogic.UpdateManager.TimeController;
import java.util.Arrays;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

/** GameEngine class : core game logic class, which holds and manipulates game entities and
 *  controls interaction with UI package
 * @author Ecem Ilgun
 * @version 1.8
 * @since 1.0
 */
public class GameEngine {
    //Variables
    static final int  MAX_LIFE = 3;
    private int numPlayer, numGhost, level, score, livesLeft;
    public int[][] gameMap;
    private double counter;
    private Pacman[] pacmans;
    private Ghost[] ghosts;
    private GameMap map;
    public boolean isPaused;
    private GamePanel gamePanel;
    private PacManMovementController pacmanMovementKeyBindings;
    private PauseGameController pauseGameController;
    private TimeController timeController;
    private UIManager uiManager;
    private GameDataManager gameDataManager;
    private GameData gameData;
    private int highScores[];
    //Constructor(s)

    /** Constructs a game engine with default configurations
     */
    public GameEngine(UIManager uiManager, int numPlayer, String name) {
        this.gameDataManager = new GameDataManager();
        this.numPlayer = numPlayer;
        if (numPlayer == 0){
            this.gameData = gameDataManager.loadGame(name);
            level = gameData.getLevel();
            score = gameData.getScore();
            livesLeft = gameData.getLivesLeft();
            ghosts = gameData.getMapData().getGhosts();
            pacmans = gameData.getMapData().getPacmans();
            this.numPlayer = pacmans.length;
            this.numGhost = ghosts.length;
            System.out.println("numplayers: " + this.numPlayer);
            System.out.println(pacmans[0]);
            gameMap = gameData.getMapData().getMapTable();
            for (int i= 0; i<11; i++)
                for (int j=0; j<20; j++)
                    if (gameMap[i][j] == -1)
                        gameMap[i][j] = 2;

        }else{
            level = 1;
            score = 0;
            livesLeft = MAX_LIFE;
            numGhost = 4;
            pacmans = new Pacman[numPlayer];
            pacmans[0] = new Pacman(PacmanType.MRPACMAN); //default pacman object for now
            if (numPlayer == 2){
                pacmans[1] = new Pacman(PacmanType.MRSPACMAN);
            }

            ghosts = new Ghost[numGhost];
            ghosts[0] = new Ghost(GhostType.BLINKY);
            ghosts[1] = new Ghost(GhostType.PINKY);
            ghosts[2] = new Ghost(GhostType.INKY);
            ghosts[3] = new Ghost(GhostType.CLYDE);

            highScores = new int[10];

            map = new GameMap();
            gameMap = map.map1;
        }

        this.uiManager = uiManager;

        this.gamePanel = new GamePanel(gameMap, pacmans, ghosts);
        uiManager.add(gamePanel, Constants.GAME_PANEL);
        counter = 3.0;
        isPaused = false;
        pacmanMovementKeyBindings = new PacManMovementController(pacmans, (numPlayer == 2), gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW), gamePanel.getActionMap());
        pacmanMovementKeyBindings.initMovementKeyBindings();

        pauseGameController = new PauseGameController(gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW), gamePanel.getActionMap(), this);
        pauseGameController.initPauseKeyBindings();

        timeController = new TimeController(this, gameMap, gamePanel.foods, pacmans, (numPlayer == 2), ghosts, gamePanel);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                startGame();
            }});

    }

    //Methods

    public void startGame(){
        gamePanel.prepareGUI();
        gamePanel.updateLives(livesLeft);
        gamePanel.updateScore(score);
//        gamePanel.repaintRequest(gameMap);
        timeController.startTimer();
    }

    private void reStartGame(){
        for (Ghost g: ghosts) {
            g.respawnInCage();
        }
        for (Pacman pm:pacmans) {
            pm.respawn();
        }
        timeController.startTimer();
    }

    /** Constructs a GameData object with all of the attributes in
     * GameEngine. Calls DataLayer.GameDataBase.GameDataManager.setGameData()
     * function in order to save this GameData object into our database
     */
    public void saveGame(String saveName) {

    }

    /** Calls startCounter(). Countdown has now started. Continues
     * the game once startCounter returns.
     */
    public void resumeGame() {
        isPaused = false;
        GameFrame.uiManager.viewGame(numPlayer, null);
        timeController.startTimer();
        //TODO
    }

    public void pauseGame() {
        timeController.stopTimer();
        isPaused = true;
        GameFrame.uiManager.viewPause();

        //TODO
    }

    public void passLevel() {
        if (level < 3) {
            level++;
        }
        else {
             gameOver();
        }
    }
    public void gameOver(){
        this.uiManager.viewGameOver();
    }

    public void addScore(int scr){
        score+=scr;
        gamePanel.updateScore(score);
    }

    public void pacmanDied() {
        livesLeft = livesLeft - 1;
        if (livesLeft == 0) {
            gameOver();
        } else {
            pacmans[0].setLivesLeft(pacmans[0].getLivesLeft() - 1);
            if (numPlayer == 2) {
                pacmans[1].setLivesLeft(pacmans[1].getLivesLeft() - 1);
            }

            gamePanel.updateLives(livesLeft);
            reStartGame();
        }
    }

    public void setHighScores(){
        boolean isChanged = false;
        if(highScores[9]==0){
            for(int i=0; i<10; i++){
                if(highScores[i]==0) {
                    highScores[i] = score;
                    isChanged = true;
                    break;
                }
            }
        }
        else{
            for(int i=0;i<10;i++){
                if (highScores[i]<score){
                    highScores[i]= score;
                    isChanged= true;
                    break;
                }
            }
        }
        if(isChanged){
            Arrays.sort(highScores);
        }
}
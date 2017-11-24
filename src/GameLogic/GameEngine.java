package GameLogic;

import GUI.GameFrame;
import GUI.GamePanel;
import GUI.UIManager;
import GameLogic.Enums.GhostType;
import GameLogic.InputManager.PacManMovementController;
import GameLogic.InputManager.PauseGameController;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;
import GameLogic.UpdateManager.TimeController;

import javax.swing.*;

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

    //Constructor(s)

    /** Constructs a game engine with default configurations
     */
    public GameEngine(UIManager uiManager, int numPlayer) {
        numPlayer = numPlayer;
        level = 1;
        score = 0;
        livesLeft = MAX_LIFE;
        numGhost = 4;

        pacmans = new Pacman[numPlayer];
        pacmans[0] = new Pacman(); //default pacman object for now

        ghosts = new Ghost[numGhost];

        ghosts[0] = new Ghost(GhostType.BLINKY);
        ghosts[1] = new Ghost(GhostType.PINKY);
        ghosts[2] = new Ghost(GhostType.INKY);
        ghosts[3] = new Ghost(GhostType.CLYDE);

        map = new GameMap();
        gameMap = map.map1;

        this.gamePanel = new GamePanel(gameMap, pacmans, ghosts);
        uiManager.add(gamePanel, Constants.GAME_PANEL);

        counter = 3.0;
        isPaused = false;
        pacmanMovementKeyBindings = new PacManMovementController(pacmans, (numPlayer == 2), gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW), gamePanel.getActionMap());
        pacmanMovementKeyBindings.initMovementKeyBindings();

        pauseGameController = new PauseGameController(gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW), gamePanel.getActionMap(), this);
        pauseGameController.initPauseKeyBindings();

        timeController = new TimeController(gameMap, gamePanel.foods, pacmans, (numPlayer == 2), ghosts, gamePanel);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                startGame();
            }});

    }


    //Methods

    public void startGame(){
        gamePanel.prepareGUI();
        gamePanel.updateLives(3);
        gamePanel.updateScore(0);
//        gamePanel.repaintRequest(gameMap);
        timeController.startTimer();
    }

    /** Constructs a GameData object with all of the attributes in
     * GameEngine. Calls DataLayer.GameDataBase.GameDataManager.setGameData()
     * function in order to save this GameData object into our database
     */
    public void saveGame() {


    }

    /** Calls startCounter(). Countdown has now started. Continues
     * the game once startCounter returns.
     */
    public void resumeGame() {
        isPaused = false;
        GameFrame.uiManager.viewGame(numPlayer);
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
//            gameOver(); //calls gameOver for now, we can change it to gameCompleted later
        }
    }

}
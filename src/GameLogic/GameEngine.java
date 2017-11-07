package GameLogic;

import GUI.GameFrame;
import GUI.GamePanel;
import GameLogic.Enums.GhostType;
import GameLogic.InputManager.PacManMovementController;
import GameLogic.InputManager.PauseGameController;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

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
    private double counter;
    private Pacman[] pacmans;
    private Ghost[] ghosts;
    private GameMap map;
    public boolean isPaused;
    private GamePanel gamePanel;
    private PacManMovementController pacmanMovementKeyBindings;
    private PauseGameController pauseGameController;

    //Constructor(s)

    /** Constructs a game engine with default configurations
     */
    public GameEngine(GamePanel gamePanel) {
        numPlayer = 1;
        level = 1;
        score = 0;
        livesLeft = MAX_LIFE;
        numGhost = 4;

        this.gamePanel = gamePanel;

        pacmans = new Pacman[numPlayer];
        pacmans[0] = new Pacman(); //default pacman object for now

        ghosts = new Ghost[numGhost];

        ghosts[0] = new Ghost(GhostType.BLINKY);
        ghosts[1] = new Ghost(GhostType.PINKY);
        ghosts[2] = new Ghost(GhostType.INKY);
        ghosts[3] = new Ghost(GhostType.CLYDE);

        map = new GameMap();

        counter = 3.0;
        isPaused = false;
        pacmanMovementKeyBindings = new PacManMovementController(pacmans, (numPlayer == 2), gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW), gamePanel.getActionMap());
        pacmanMovementKeyBindings.initMovementKeyBindings();

        pauseGameController = new PauseGameController(gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW), gamePanel.getActionMap(), this);
        pauseGameController.initPauseKeyBindings();
    }


    //Methods

    /** Constructs a GameData object with all of the attributes in
     * GameEngine. Calls DataLayer.GameDataBase.GameDataManager.setGameData()
     * function in order to save this GameData object into our database
     */
    public void saveGame() {


    }

    /** Calls startCounter(). Countdown has now started. Continues
     * the game once startCounter returns.
     */
    public void resume() {
        isPaused = false;
        //TODO
    }

    public void pause() {
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
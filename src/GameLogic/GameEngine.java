package GameLogic;

import GameLogic.ScreenItems.*;
import GameLogic.Enums.*;

/** GameEngine class :
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
    private boolean isPaused;

    //Constructor(s)

    /** Constructs a game engine with default configurations
     */
    public GameEngine() {
        numPlayer = 1;
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

        counter = 3.0;
        isPaused = true;
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
        //TODO
    }

    public void passLevel() {
        if (level < 3) {

            level++;
        }
        else
            gameOver(); //calls gameOver for now, we can change it to gameCompleted later
    }

}
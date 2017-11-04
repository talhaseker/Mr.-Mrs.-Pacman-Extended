package GameLogic;

import GameLogic.ScreenItems.Pacman;
import GameLogic.ScreenItems.PacmanObject;

import java.util.List;

public class GameEngine {
    //Variables
    static final int  MAX_LIFE = 3;

    private int numPlayer, level, score, livesLeft;
    private double counter;
    private Pacman[] pacmans;
    private int[][] foodInMap, wallsInGame;
    private List<PacmanObject> gameItemsInMap;
    private boolean isPaused;

    //Constructor(s)

    /** Constructs a game engine with default configurations
     */
    public GameEngine() {
        numPlayer = 1;
        level = 1;
        score = 0;
        livesLeft = MAX_LIFE;

        pacmans = new Pacman[numPlayer];
        pacmans[0] = new Pacman(); //default pacman object for now
        //foodInMap = ;
        //wallsInGame = ;
        //gameItemsInMap = ;

        counter = 3.0;
        isPaused = true;
    }


    //Methods

    /** Constructs a GameData object with all of the attributes in
     * GameEngine. Calls DataLayer.GameDataBase.GameDataManager.setGameData()
     * function in order to save this GameData object into our database
     */
    public void saveGame() {
        //TODO: will be implemented after data-layer
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
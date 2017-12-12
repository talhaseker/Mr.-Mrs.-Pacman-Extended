package GameLogic.UpdateManager;

import GUI.GamePanel;
import GameLogic.Enums.PacmanType;
import GameLogic.ScreenItems.Food;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import java.util.ArrayList;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class UpdateService {

    private boolean isMultiplayer;
    private Pacman[] pacmans;
    private Ghost[] ghosts;
    private GamePanel gamePanel;
    private GhostController ghostController;
    private int[][] gameMap;
    private ArrayList<Food> foods;
    private InteractionCheckerAndHandler interactionCheckerAndHandler;
    private int pacMrMoveThreshold = 10;
    private int pacMrsMoveThreshold = 10;

    public UpdateService(int[][] gameMap, ArrayList<Food> foods, Pacman[] pacmans, boolean isMultiplayer, Ghost[] ghosts, GamePanel gamePanel){
        this.isMultiplayer = isMultiplayer;
        this.pacmans = pacmans;
        this.ghosts = ghosts;
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
        this.foods = foods;
        this.interactionCheckerAndHandler = new InteractionCheckerAndHandler(gameMap, foods, pacmans, ghosts);
        this.ghostController = new GhostController(interactionCheckerAndHandler, gameMap, ghosts, pacmans[0]);
    }

    public void updateObjects(){

        updatePacman(pacmans[0]);
        if (isMultiplayer){
            updatePacman(pacmans[1]);
        }

        ghostController.move();
        for (Ghost g: ghosts){
            if (interactionCheckerAndHandler.isMoveAllowed(g, g.lastMovement)){
            switch (g.lastMovement){
                case LEFT:
                    g.setXpos(g.getXpos() - g.getSpeed());
                    break;
                case RIGHT:
                    g.setXpos(g.getXpos() + g.getSpeed());
                    break;
                case UP:
                    g.setYpos(g.getYpos() - g.getSpeed());
                    break;
                case DOWN:
                    g.setYpos(g.getYpos() + g.getSpeed());
                    break;
                default:
                    break;
            }}
        }
        gamePanel.repaintRequest(gameMap);
    }

    private void updatePacman(Pacman pm){
        if (pm.getPacmanType() == PacmanType.MRPACMAN)
            if (pm.curMovement != pm.lastMovement && interactionCheckerAndHandler.isMoveAllowed(pm, pm.curMovement)){
                pm.changeMovement();
            }else if (pacMrMoveThreshold == 0){
                pm.curMovement = pm.lastMovement;
                pacMrMoveThreshold = 10;
            }else {
                pacMrMoveThreshold--;
            }

        if (pm.getPacmanType() == PacmanType.MRSPACMAN)
            if (pm.curMovement != pm.lastMovement && interactionCheckerAndHandler.isMoveAllowed(pm, pm.curMovement)){
                pm.changeMovement();
            }else if (pacMrsMoveThreshold == 0){
                pm.curMovement = pm.lastMovement;
                pacMrsMoveThreshold = 10;
            }else {
                pacMrsMoveThreshold--;
            }

        if (interactionCheckerAndHandler.isMoveAllowed(pm, pm.lastMovement)) {
            switch (pm.lastMovement) {
                case LEFT:
                    pm.setXpos(pm.getXpos() - pm.getSpeed());
                    break;
                case RIGHT:
                    pm.setXpos(pm.getXpos() + pm.getSpeed());
                    break;
                case UP:
                    pm.setYpos(pm.getYpos() - pm.getSpeed());
                    break;
                case DOWN:
                    pm.setYpos(pm.getYpos() + pm.getSpeed());
                    break;
                default:
                    break;
            }
        }
    }
}

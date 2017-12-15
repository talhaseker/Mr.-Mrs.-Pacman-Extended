package GameLogic.UpdateManager;

import GUI.GamePanel;
import GameLogic.Enums.PacmanType;
import GameLogic.GameEngine;
import GameLogic.ScreenItems.Food;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import java.util.ArrayList;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class UpdateService {

    private boolean isMultiplayer;
    private GameEngine ge;
    private Pacman[] pacmans;
    private Ghost[] ghosts;
    private GamePanel gamePanel;
    private GhostController ghostController;
    private int[][] gameMap;
    private ArrayList<Food> foods;
    private InteractionCheckerAndHandler interactionCheckerAndHandler;
    private int pacMrMoveThreshold = 20;
    private int pacMrsMoveThreshold = 20;

    public UpdateService(GameEngine ge,int[][] gameMap, ArrayList<Food> foods, Pacman[] pacmans, boolean isMultiplayer, Ghost[] ghosts, GamePanel gamePanel){
        this.ge = ge;
        this.isMultiplayer = isMultiplayer;
        this.pacmans = pacmans;
        this.ghosts = ghosts;
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
        this.foods = foods;
        this.interactionCheckerAndHandler = new InteractionCheckerAndHandler(isMultiplayer, gameMap, foods, pacmans, ghosts);
        this.ghostController = new GhostController(interactionCheckerAndHandler, gameMap, ghosts, pacmans[0]);
    }

    public void updateObjects(){
//        System.out.println("map here!");
//        for (int i=0; i<11; i++){
//            System.out.println((i + 1) + "th row");
//            for (int j=0; j<20; j++){
//                System.out.print(", " +gameMap[i][j]);
//            }
//        }

        boolean canEat = pacmans[0].isCanEatGhost();
        ge.addScore(interactionCheckerAndHandler.doEatFood());
        updatePacman(pacmans[0]);
        if (isMultiplayer){
            updatePacman(pacmans[1]);
            canEat = canEat || pacmans[1].isCanEatGhost();
        }
        System.out.println(canEat);
        if (interactionCheckerAndHandler.doBumpGhosts(canEat)){
            if (canEat)
                ge.addScore(100);
            else
                ge.pacmanDied();
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
                pacMrMoveThreshold = 20;
            }else {
                pacMrMoveThreshold--;
            }

        if (pm.getPacmanType() == PacmanType.MRSPACMAN)
            if (pm.curMovement != pm.lastMovement && interactionCheckerAndHandler.isMoveAllowed(pm, pm.curMovement)){
                pm.changeMovement();
            }else if (pacMrsMoveThreshold == 0){
                pm.curMovement = pm.lastMovement;
                pacMrsMoveThreshold = 20;
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

    /**
     * Updates MovingObjects' images according to give the effect of animation
     */
    public void updateObjectAnimations(){}

}

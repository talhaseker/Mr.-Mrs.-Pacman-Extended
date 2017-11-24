package GameLogic.UpdateManager;

import GUI.GamePanel;
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

    public UpdateService(int[][] gameMap, ArrayList<Food> foods, Pacman[] pacmans, boolean isMultiplayer, Ghost[] ghosts, GamePanel gamePanel){
        this.isMultiplayer = isMultiplayer;
        this.pacmans = pacmans;
        this.ghosts = ghosts;
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
        this.foods = foods;
        this.ghostController = new GhostController(gameMap, ghosts, pacmans[0]);
        this.interactionCheckerAndHandler = new InteractionCheckerAndHandler(gameMap, foods, pacmans, ghosts);
    }

    public void updateObjects(){
        Pacman pm = pacmans[0];
            switch (pm.curMovement){
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

        if (isMultiplayer){
            Pacman pm2 = pacmans[1];
            switch (pm.curMovement){
                case LEFT:
                    pm2.setXpos(pm.getXpos() - pm.getSpeed());
                    break;
                case RIGHT:
                    pm2.setXpos(pm.getXpos() + pm.getSpeed());
                    break;
                case UP:
                    pm2.setYpos(pm.getYpos() - pm.getSpeed());
                    break;
                case DOWN:
                    pm2.setYpos(pm.getYpos() + pm.getSpeed());
                    break;
                default:
                    break;
            }
        }
        ghostController.move();
        for (Ghost g: ghosts){
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
            }
        }
        gamePanel.repaintRequest(gameMap);
    }
}

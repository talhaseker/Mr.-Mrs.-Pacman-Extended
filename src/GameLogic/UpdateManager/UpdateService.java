package GameLogic.UpdateManager;

import GUI.GamePanel;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class UpdateService {

    private boolean isMultiplayer;
    private Pacman[] pacmans;
    private Ghost[] ghosts;
    private GamePanel gamePanel;

    public UpdateService(Pacman[] pacmans, boolean isMultiplayer, Ghost[] ghosts, GamePanel gamePanel){
        this.isMultiplayer = isMultiplayer;
        this.pacmans = pacmans;
        this.ghosts = ghosts;
        this.gamePanel = gamePanel;
    }

    public void updateObjects(){
//        gamePanel.requestRepaint();
    }
}

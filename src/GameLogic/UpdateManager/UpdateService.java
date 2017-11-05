package GameLogic.UpdateManager;

import GUI.GamePanel;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class UpdateService {

    private boolean isMultiplayer;
    private ArrayList<Pacman> pacmans;
    private List<Ghost> ghosts;
    private GamePanel gamePanel;

    public UpdateService(ArrayList<Pacman> pacmans, boolean isMultiplayer, List<Ghost> ghosts, GamePanel gamePanel){
        this.isMultiplayer = isMultiplayer;
        this.pacmans = pacmans;
        this.ghosts = ghosts;
        this.gamePanel = gamePanel;
    }

    public void updateObjects(){
//        gamePanel.requestRepaint();
    }
}

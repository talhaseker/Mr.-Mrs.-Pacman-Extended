package GameLogic.InputManager;

import GUI.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class PauseGameController {

    // PROPERTIES
    private InputMap inputMap;
    private ActionMap actionMap;
    private GamePanel gamePanel;

    public PauseGameController(InputMap inputMap, ActionMap actionMap, GamePanel gamePanel){
        this.inputMap = inputMap;
        this.actionMap = actionMap;
        this.gamePanel = gamePanel;
    }

    public void initPauseKeyBindings() {

        // ESC KEY
        KeyStroke keyStrokeEscPressed = KeyStroke.getKeyStroke(
                KeyEvent.VK_ESCAPE, 0, false);
        inputMap.put(keyStrokeEscPressed, "esc.pressed");
        actionMap.put("esc.pressed", new PauseAction());
    }

    @SuppressWarnings("serial")
    public class PauseAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

//            if (!gamePanel.isPaused()) { // pause request
//                gamePanel.pauseGame();
//            }
//            else { // resume request
//                gamePanel.resumeGame();
//            }
        }
    }
}

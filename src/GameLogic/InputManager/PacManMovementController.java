package GameLogic.InputManager;

import GameLogic.Enums.Movement;
import GameLogic.ScreenItems.Pacman;
import GameLogic.UpdateManager.InteractionCheckerAndHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class PacManMovementController {

//    pacmanMovementKeyBindings = new PacManMovementController(
//            pacmans, false, getInputMap(WHEN_IN_FOCUSED_WINDOW), getActionMap());
//    pacmanMovementKeyBindings.initMovementKeyBindings();

    // PROPERTIES
    private Pacman[] pacmans;
    private InputMap inputMap;
    private ActionMap actionMap;
    private boolean isMultiplayer;

    // CONSTRUCTOR
    public PacManMovementController(Pacman[] pacmans, boolean isMultiplayer, InputMap inputMap, ActionMap actionMap) {
        this.pacmans = pacmans;
        this.inputMap = inputMap;
        this.actionMap = actionMap;
        this.isMultiplayer = isMultiplayer;
    }

    // METHODS
    public void initMovementKeyBindings() {

        // right pressed
        KeyStroke keyStrokeRightPressed = KeyStroke.getKeyStroke(
                KeyEvent.VK_RIGHT, 0, false);
        inputMap.put(keyStrokeRightPressed, "right.pressed");
        actionMap.put("right.pressed", new PacManMovementAction(
                pacmans[0], Movement.RIGHT));

        // left pressed
        KeyStroke keyStrokeLeftPressed = KeyStroke.getKeyStroke(
                KeyEvent.VK_LEFT, 0, false);
        inputMap.put(keyStrokeLeftPressed, "left.pressed");
        actionMap.put("left.pressed", new PacManMovementAction(
                pacmans[0], Movement.LEFT));

        // up pressed
        KeyStroke keyStrokeUpPressed = KeyStroke.getKeyStroke(
                KeyEvent.VK_UP, 0, false);
        inputMap.put(keyStrokeUpPressed, "up.pressed");
        actionMap.put("up.pressed", new PacManMovementAction(
                pacmans[0], Movement.UP));

        // up pressed
        KeyStroke keyStrokeDownPressed = KeyStroke.getKeyStroke(
                KeyEvent.VK_DOWN, 0, false);
        inputMap.put(keyStrokeDownPressed, "down.pressed");
        actionMap.put("down.pressed", new PacManMovementAction(
                pacmans[0], Movement.DOWN));

        if (isMultiplayer){
            // right released
            KeyStroke keyStrokeRightReleased = KeyStroke.getKeyStroke(
                    KeyEvent.VK_D, 0, false);
            inputMap.put(keyStrokeRightReleased, "rightsecond.pressed");
            actionMap.put("rightsecond.pressed", new PacManMovementAction(
                    pacmans[1], Movement.RIGHT));

            // left released
            KeyStroke keyStrokeLeftReleased = KeyStroke.getKeyStroke(
                    KeyEvent.VK_A, 0, false);
            inputMap.put(keyStrokeLeftReleased, "leftsecond.pressed");
            actionMap.put("leftsecond.pressed", new PacManMovementAction(
                    pacmans[1], Movement.LEFT));

            // up released
            KeyStroke keyStrokeUpReleased = KeyStroke.getKeyStroke(
                    KeyEvent.VK_W, 0, false);
            inputMap.put(keyStrokeUpReleased, "upsecond.pressed");
            actionMap.put("upsecond.pressed", new PacManMovementAction(
                    pacmans[1], Movement.UP));

            // down released
            KeyStroke keyStrokeDownReleased = KeyStroke.getKeyStroke(
                    KeyEvent.VK_S, 0, false);
            inputMap.put(keyStrokeDownReleased, "downsecond.pressed");
            actionMap.put("downsecond.pressed", new PacManMovementAction(
                    pacmans[1], Movement.DOWN));
        }
    }

    @SuppressWarnings("serial")
    public class PacManMovementAction extends AbstractAction {

        private Movement movement;
        private Pacman pacman;

        public PacManMovementAction(Pacman pacman, Movement movement) {
            this.pacman = pacman;
            this.movement = movement;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isAllowed = InteractionCheckerAndHandler.isMoveAllowed(pacman, movement);
            if (isAllowed)
                pacman.curMovement = movement;
        }
    }
}

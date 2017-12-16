package GameLogic.UpdateManager;

import GameLogic.Enums.Movement;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import java.util.Random;

import static GameLogic.Constants.COUNTDOWN;
import static GameLogic.Constants.SCATTER;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class GhostController {

    private Ghost[] ghosts;
    private Pacman pm;
    private int[][] gameMap;
    int pacLives = 3;

    final int cageBeginX = 344;
    final int cageEndX = 456;
    final int cageBeginY = 236;
    final int cageEndY = 264;
    final int gameEndX = 680;
    final int gameEndY = 404;

    int cageTimerInky, cageTimerPinky, cageTimerBlinky, cageTimerClyde = 0;
    boolean MovementUPInky, MovementUPPinky, MovementUPBlinky, MovementUPClyde = false;
    int mod = 7;
    int deathTimer = 40;

    int minDistance = 100;
    Random numGen = new Random();
    boolean moveOpposite = false;
    int oppCounter = 0;

    InteractionCheckerAndHandler interactionCheckerAndHandler;

    public GhostController(InteractionCheckerAndHandler interactionCheckerAndHandler, int[][] gameMap, Ghost[] ghosts, Pacman pm){
        this.interactionCheckerAndHandler = interactionCheckerAndHandler;
        this.gameMap = gameMap;
        this.ghosts = ghosts;
        this.pm = pm;
    }

    public void move(){
        for (Ghost ghost:ghosts) {
            specificMove(ghost);
        }
        if(pm.getLivesLeft() != pacLives){
            pacLives = pm.getLivesLeft();
        }
    }

    private void specificMove(Ghost ghost){
        switch (ghost.getGhostType()){
            case PINKY:
                pinkyMove(ghost);
                break;
            case CLYDE:
                clydeMove(ghost);
                break;
            case BLINKY:
                blinkyMove(ghost);
                break;
            case INKY:
                inkyMove(ghost);
                break;
            default:
                break;
        }

    }

    private void inkyMove(Ghost ghost){

        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(pm.getLivesLeft() != pacLives){
            cageTimerInky = COUNTDOWN;
            ghost.isAttacking = false;
            ghost.countdownTimer = SCATTER;
        }

        if(cageTimerInky > 0){
            if(cageTimerInky%mod==0){
                if(MovementUPInky){
                    ghost.curMovement = Movement.LEFT;
                }
                else{
                    ghost.curMovement = Movement.RIGHT;
                }
                MovementUPInky = !MovementUPInky;
            }
            cageTimerInky --;
            if(cageTimerInky <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            if ((curY >= cageBeginY && curY <= cageEndY) && (curX >= cageBeginX && curX <= cageEndX)) {
                cageTimerInky = deathTimer;
            }
            int targetX = cageBeginX+28, targetY = cageBeginY;
            if(ghost.isScattered()){
                targetX = gameEndX - pm.getXpos();
                targetY = gameEndY - pm.getYpos();
            } else if(!ghost.isAttacking){
                targetX = gameEndX - pm.getXpos();
                targetY = gameEndY - pm.getYpos();
                ghost.countdownTimer --;
            } else {
                targetX = pm.getXpos();
                targetY = pm.getYpos();
                ghost.countdownTimer --;
            }

            if(ghost.countdownTimer <= 0){
                ghost.flipAttack();
            }
            tryToMove(ghost, curX, curY, targetX, targetY);

        }
        ghost.lastMovement = ghost.curMovement;
    }

    private void pinkyMove(Ghost ghost){
        int minDistance = 100;

        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(pm.getLivesLeft() != pacLives){
            cageTimerPinky = COUNTDOWN;
            ghost.isAttacking = false;
            ghost.countdownTimer = SCATTER;
        }

        if(cageTimerPinky > 0){
            if(cageTimerPinky%mod==0){
                if(MovementUPPinky){
                    ghost.curMovement = Movement.LEFT;
                }
                else{
                    ghost.curMovement = Movement.RIGHT;
                }
                MovementUPPinky = !MovementUPPinky;
            }
            cageTimerPinky --;
            if(cageTimerPinky <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            // check to see if in center (just spawned)
            if ((curY >= cageBeginY && curY <= cageEndY) && (curX >= cageBeginX && curX <= cageEndX)) {
                cageTimerPinky = deathTimer;
            }

            int targetX = cageBeginX+28, targetY = cageBeginY;
            if(ghost.isScattered()){
                targetX = gameEndX - pm.getXpos();
                targetY = gameEndY - pm.getYpos();
            }  else if(!ghost.isAttacking){
                targetX = gameEndX - pm.getXpos();
                targetY = gameEndY - pm.getYpos();
                ghost.countdownTimer --;
            }else {
                targetX = pm.getXpos();
                targetY = pm.getYpos();
                ghost.countdownTimer --;
            }
            if(ghost.countdownTimer <= 0){
                ghost.flipAttack();
            }
            // if pinky is within a set distance from pacman
            if(getDistanceToPacman(curX, curY, targetX, targetY) < minDistance){
                // it tries to go the same Movement as pacman
                try{
                    ghost.curMovement = pm.curMovement;
                }
                // just incase something goes wrong, it sets the Movement as the last Movement
                catch(NullPointerException NPE){
                    ghost.curMovement = ghost.lastMovement;
                }
                // and if it can't go that Movement, it'll just move according to the standard
                // ai and try to eat pacman
                if(!moveIsAllowed(ghost, ghost.curMovement)){
                    tryToMove(ghost, curX, curY, targetX, targetY);
                }
            }
            else{
                tryToMove(ghost, curX, curY, targetX, targetY);
            }

        }
        ghost.lastMovement = ghost.curMovement;
    }

    private void blinkyMove(Ghost ghost){

        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(pm.getLivesLeft() != pacLives){
            cageTimerBlinky = COUNTDOWN;
            ghost.isAttacking = false;
            ghost.countdownTimer = SCATTER;
        }

        if(cageTimerBlinky > 0){
            if(cageTimerBlinky%mod==0){
                if(MovementUPBlinky){
                    ghost.curMovement = Movement.LEFT;
                }
                else{
                    ghost.curMovement = Movement.RIGHT;
                }
                MovementUPBlinky = !MovementUPBlinky;
            }
            cageTimerBlinky --;
            if(cageTimerBlinky <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            if ((curY >= cageBeginY && curY <= cageEndY) && (curX >= cageBeginX && curX <= cageEndX)) {
                cageTimerBlinky = deathTimer;
            }
            int targetX = numGen.nextInt(560) + 120, targetY = numGen.nextInt(308) + 96;
            if(ghost.isScattered()){
                targetX = gameEndX - pm.getXpos();
                targetY = gameEndY - pm.getYpos();
            } else if(!ghost.isAttacking){
                targetX = gameEndX - pm.getXpos();
                targetY = gameEndY - pm.getYpos();
                ghost.countdownTimer --;
            }else {
                ghost.countdownTimer --;
            }

            if(ghost.countdownTimer <= 0){
                ghost.flipAttack();
            }

            tryToMove(ghost, curX, curY, targetX, targetY);

        }
        ghost.lastMovement = ghost.curMovement;
    }

    private void clydeMove(Ghost ghost){

        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(pm.getLivesLeft() != pacLives){
            cageTimerClyde = COUNTDOWN;
            ghost.isAttacking = false;
            ghost.countdownTimer = SCATTER;
        }

        if(cageTimerClyde > 0){
            if(cageTimerClyde%mod==0){
                if(MovementUPClyde){
                    ghost.curMovement = Movement.LEFT;
                }
                else{
                    ghost.curMovement = Movement.RIGHT;
                }
                MovementUPClyde = !MovementUPClyde;
            }
            cageTimerClyde --;
            if(cageTimerClyde <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            if ((curY >= cageBeginY && curY <= cageEndY) && (curX >= cageBeginX && curX <= cageEndX)) {
                cageTimerClyde = deathTimer;
            }
            int targetX = cageBeginX+28, targetY = cageBeginY;
            if(ghost.isScattered()){
                targetX = gameEndX - pm.getXpos();
                targetY = gameEndY - pm.getYpos();
            }  else if(!ghost.isAttacking){
                targetX = gameEndX - pm.getXpos();
                targetY = gameEndY - pm.getYpos();
                ghost.countdownTimer --;
            }else {
                targetX = pm.getXpos();
                targetY = pm.getYpos();
                if (getDistanceToPacman(curX, curY, targetX, targetY) > minDistance){
                    try{
                        ghost.curMovement = pm.curMovement;
                    }
                    catch(NullPointerException NPE){
                        ghost.curMovement = ghost.lastMovement;
                    }
                    if (moveOpposite) {
                        if (oppCounter++ == 50) {
                            moveOpposite = false;
                            oppCounter = 0;
                        }
                        targetX = gameEndX - targetX;
                        targetY = gameEndY - targetY;
                    } else {
                        if (numGen.nextInt(40) == 1) { // 1 in 40 chance
                            moveOpposite = true;
                        }
                        if(ghost.curMovement == Movement.UP){
                            targetY -= minDistance/2;
                        }
                        else if(ghost.curMovement == Movement.DOWN){
                            targetY += minDistance/2;
                        }
                        else if(ghost.curMovement == Movement.LEFT){
                            targetX -= minDistance/2;
                        }
                        else{
                            targetX += minDistance/2;
                        }
                    }
                }
                ghost.countdownTimer --;
            }
            if(ghost.countdownTimer <= 0){
                ghost.flipAttack();
            }
            tryToMove(ghost, curX, curY, targetX, targetY);

        }
        ghost.lastMovement = ghost.curMovement;
    }

    private double getDistanceToPacman(int Gx, int Gy, int Px, int Py){
        double distance = 0;
        distance = Math.sqrt(Math.pow((Px - Gx), 2) + Math.pow((Py - Gy), 2));
        return distance;
    }

    private void tryToMove (Ghost ghost, int curX, int curY, int targetX, int targetY){
        int horizontalDifference = curX - targetX;
        int verticalDifference = curY - targetY;
        Movement preferredHorizontal = horizontalDifference > 0 ? Movement.LEFT : Movement.RIGHT;
        Movement preferredVertical = verticalDifference > 0 ? Movement.UP : Movement.DOWN;
        boolean verticalMoreImportant = Math.abs(verticalDifference) > Math.abs(horizontalDifference);
        if (verticalMoreImportant)
            ghost.curMovement = preferredVertical;
        else
            ghost.curMovement = preferredHorizontal;
        if (!moveIsAllowed(ghost, ghost.curMovement)) {
            if (verticalMoreImportant) {
                if (ghost.lastMovement == Movement.LEFT || ghost.lastMovement == Movement.RIGHT) {
                    ghost.curMovement = ghost.lastMovement;
                    if (!moveIsAllowed(ghost, ghost.curMovement))
                        ghost.curMovement = ghost.curMovement == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                } else {
                    ghost.curMovement = preferredHorizontal;
                    if (!moveIsAllowed(ghost, ghost.curMovement)) {
                        ghost.curMovement = preferredHorizontal == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                        if (!moveIsAllowed(ghost, ghost.curMovement))
                            ghost.curMovement = preferredVertical == Movement.UP ? Movement.DOWN : Movement.UP;
                    }
                }
            } else {
                if (ghost.lastMovement == Movement.UP || ghost.lastMovement == Movement.DOWN) {
                    ghost.curMovement = ghost.lastMovement;
                    if (!moveIsAllowed(ghost, ghost.curMovement))
                        ghost.curMovement = ghost.curMovement == Movement.UP ? Movement.DOWN : Movement.UP;
                } else {
                    ghost.curMovement = preferredVertical;
                    if (!moveIsAllowed(ghost, ghost.curMovement)) {
                        ghost.curMovement = preferredVertical == Movement.UP ? Movement.DOWN : Movement.UP;
                        if (!moveIsAllowed(ghost, ghost.curMovement))
                            ghost.curMovement = preferredHorizontal == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                    }
                }
            }
        }
    }

    private boolean moveIsAllowed(Ghost ghost, Movement curMovement){
        return interactionCheckerAndHandler.isMoveAllowed(ghost, curMovement, false);
    }
}

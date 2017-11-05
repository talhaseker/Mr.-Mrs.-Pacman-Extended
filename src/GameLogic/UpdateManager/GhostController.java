package GameLogic.UpdateManager;

import GameLogic.Enums.Movement;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import java.util.List;
import java.util.Random;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class GhostController {

    private List<Ghost> ghosts;
    private Pacman pm;

    public GhostController(List<Ghost> ghosts, Pacman pm){
        this.ghosts = ghosts;
        this.pm = pm;
    }

    public void move(){
        for (Ghost ghost:ghosts) {
            specificMove(ghost);
        }
    }

    private void specificMove(Ghost ghost){
        switch (ghost.getGhostType()){
            case PINKY:
                break;
            case CLYDE:
                clydeMove(ghost);
                break;
            case BLINKY:
                break;
            case INKY:
                break;
            default:
                break;
        }

    }

    private void clydeMove(Ghost ghost){

        final int COUNTDOWN = 150;
        int cageTimer = 0;
        boolean MovementUP = false;
        int mod = 7;
        int deathTimer = 40;
        int minDistance = 100;
        int pacLives = 5;
        Random numGen = new Random();
        boolean moveOpposite = false;
        int oppCounter = 0;


        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(cageTimer > 0){
            if(cageTimer%mod==0){
                if(MovementUP){
                    ghost.curMovement = Movement.UP;
                }
                else{
                    ghost.curMovement = Movement.DOWN;
                }
                MovementUP = !MovementUP;
            }
            cageTimer --;
            if(cageTimer <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            if ((curY > 249 && curY <= 310) && (curX >= 230 && curX <= 370)) {
                cageTimer = deathTimer;
            }
            int targetX = 250, targetY = 350;
            if(ghost.isScattered()){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
            }  else if(!ghost.isAttacking){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
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
                        targetX = 558 - targetX;
                        targetY = 551 - targetY;
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
        if (!moveIsAllowed(ghost.curMovement)) {
            if (verticalMoreImportant) {
                if (ghost.lastMovement == Movement.LEFT || ghost.lastMovement == Movement.RIGHT) {
                    ghost.curMovement = ghost.lastMovement;
                    if (!moveIsAllowed(ghost.curMovement))
                        ghost.curMovement = ghost.curMovement == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                } else {
                    ghost.curMovement = preferredHorizontal;
                    if (!moveIsAllowed(ghost.curMovement)) {
                        ghost.curMovement = preferredHorizontal == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                        if (!moveIsAllowed(ghost.curMovement))
                            ghost.curMovement = preferredVertical == Movement.UP ? Movement.DOWN : Movement.UP;
                    }
                }
            } else {
                if (ghost.lastMovement == Movement.UP || ghost.lastMovement == Movement.DOWN) {
                    ghost.curMovement = ghost.lastMovement;
                    if (!moveIsAllowed(ghost.curMovement))
                        ghost.curMovement = ghost.curMovement == Movement.UP ? Movement.DOWN : Movement.UP;
                } else {
                    ghost.curMovement = preferredVertical;
                    if (!moveIsAllowed(ghost.curMovement)) {
                        ghost.curMovement = preferredVertical == Movement.UP ? Movement.DOWN : Movement.UP;
                        if (!moveIsAllowed(ghost.curMovement))
                            ghost.curMovement = preferredHorizontal == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                    }
                }
            }
        }
    }

    private boolean moveIsAllowed(Movement curMovement){
        return true;
    }
}

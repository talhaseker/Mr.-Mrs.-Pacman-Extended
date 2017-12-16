package GameLogic.UpdateManager;

import GameLogic.Enums.Movement;
import GameLogic.ScreenItems.Food;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.MovingObject;
import GameLogic.ScreenItems.Pacman;

import java.util.ArrayList;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class InteractionCheckerAndHandler {

    final int initialX = 120, initialY = 96;
    boolean isMultiplayer;
    int[][] gameMap;
    ArrayList<Food> foods;
    Pacman[] pacmans;
    Ghost[] ghosts;

    public InteractionCheckerAndHandler(boolean isMultiplayer, int[][] gameMap, ArrayList<Food> foods, Pacman[] pacmans, Ghost[] ghosts){
        this.isMultiplayer = isMultiplayer;
        this.foods = foods;
        this.gameMap = gameMap;
        this.ghosts = ghosts;
        this.pacmans = pacmans;
    }

    public boolean isMoveAllowed(MovingObject movingObject, Movement curMovement){
        int currentX = movingObject.getXpos() - initialX;
        int currentY = movingObject.getYpos() - initialY;

        int firstIndexRows = currentY/28 -1 > 0 ? currentY/28 -1 : 0;
        int lastIndexRows = currentY/28 +2 < 10 ? currentY/28 +2 : 10;
        int firstIndexColumns = currentX/28 -1 > 0 ? currentX/28 -1 : 0;
        int lastIndexColumns = currentX/28 +2 < 19 ? currentX/28 +2 : 19;

        switch (curMovement){
            case UP:
                if (firstIndexColumns+1 <= lastIndexColumns && (firstIndexColumns+1)*28 == currentX){
                    if (gameMap[firstIndexRows][firstIndexColumns+1] == 1){
                        return firstIndexRows + 1 <= lastIndexRows && (firstIndexRows + 1) * 28 < currentY;
                    }else{return true;}
                }else{
                    return false;
                }
            case RIGHT:
                if (firstIndexRows+1 <= lastIndexRows && (firstIndexRows+1)*28 == currentY){
                    if (firstIndexColumns+2 <= lastIndexColumns && gameMap[firstIndexRows+1][firstIndexColumns+2] == 1){
                        return false;
                    }else{
                        if (firstIndexColumns+3 <= lastIndexColumns && gameMap[firstIndexRows+1][firstIndexColumns+3] == 1){
                            if ((firstIndexColumns+3)*28 > currentX){
                                return true;}
                        }

                        return true;
                    }
                }else{
                    return false;
                }
            case DOWN:
                if (firstIndexColumns+1 <= lastIndexColumns && (firstIndexColumns+1)*28 == currentX){
                    if (firstIndexRows+2 <= lastIndexRows && (gameMap[firstIndexRows+2][firstIndexColumns+1] == 1 || gameMap[firstIndexRows+2][firstIndexColumns+1] == 0)){
                        return false;
                    }else{
                        if (firstIndexRows+3 <= lastIndexRows && (gameMap[firstIndexRows+3][firstIndexColumns+1] == 1 || gameMap[firstIndexRows+3][firstIndexColumns+1] == 0))
                            if ((firstIndexRows+3)*28 > currentY)
                                return true;
                        return true;
                    }
                }else{
                    return false;
                }
            case LEFT:
                if (firstIndexRows+1 <= lastIndexRows && (firstIndexRows+1)*28 == currentY){
                    //TODO: "indexOutOfBoundsException : 25" occured on if statement below, find why
                    if (gameMap[firstIndexRows+1][firstIndexColumns] == 1){
                        return firstIndexColumns + 1 <= lastIndexColumns && (firstIndexColumns + 1) * 28 < currentX;
                    }else{return true;}
                }else{
                    return false;
                }
        }

        return true;
    }

    public int doEatFood(){
        if (isMultiplayer){
            return checkPandFood(pacmans[0]) + checkPandFood(pacmans[1]);
        }
        return checkPandFood(pacmans[0]);
    }

    private int checkPandFood(Pacman pm){
        boolean retVal = false;
        int score = 0;
        int firstIndexY = (pm.getYpos() - 96)/28;
        int firstIndexX = (pm.getXpos() - 120)/28;
        ArrayList<Food> foodsToRemove;

        if ((pm.getYpos() - 96)%28 == 14){
            foodsToRemove = new ArrayList<Food>();
            for (Food f: foods) {
                if ((f.getYpos() == (pm.getYpos() - 14) || f.getYpos() == (pm.getYpos() + 14)) && f.getXpos() == pm.getXpos()){
                    foodsToRemove.add(f);
                    pm.eatFood(f);
                    retVal = true;
                }
            }
            for (Food f: foodsToRemove) {
                score += f.getPoints();
                foods.remove(f);
            }

            switch (gameMap[firstIndexY][firstIndexX]){
                case 2:case 3:case 4: case 5:
                    gameMap[firstIndexY][firstIndexX] = -1;
                    break;
            }
            switch (gameMap[firstIndexY+1][firstIndexX]){
                case 2:case 3:case 4: case 5:
                    gameMap[firstIndexY+1][firstIndexX] = -1;
                    break;
            }
        }

        if ( (pm.getXpos() - 120)%28 == 14){
            foodsToRemove = new ArrayList<Food>();
            for (Food f: foods) {
                if ((f.getXpos() == (pm.getXpos() - 14) || f.getXpos() == (pm.getXpos() + 14)) && f.getYpos() == pm.getYpos()){
                    foodsToRemove.add(f);
                    if (f.getSideEffect() == 1)
                        for (Ghost g: ghosts) {
                            g.scatter();
                        }
                    pm.eatFood(f);
                    retVal = true;
                }
            }
            for (Food f: foodsToRemove) {
                score += f.getPoints();
                foods.remove(f);
            }
            switch (gameMap[firstIndexY][firstIndexX]){
                case 2:case 3:case 4: case 5:
                    gameMap[firstIndexY][firstIndexX] = -1;
                    break;
            }
            switch (gameMap[firstIndexY][firstIndexX+1]){
                case 2:case 3:case 4: case 5:
                    gameMap[firstIndexY][firstIndexX+1] = -1;
                    break;
            }
        }

        return score;
    }

    public boolean doBumpGhosts(boolean canEat){
        if (isMultiplayer){
            return checkPandGhosts(pacmans[0], canEat) || checkPandGhosts(pacmans[1], canEat);
        }
        return checkPandGhosts(pacmans[0], canEat);
    }

    private boolean checkPandGhosts(Pacman pm, boolean canEat){
        for (int i = 0; i < ghosts.length; i++) {
            int xDif = Math.abs(ghosts[i].getXpos()-pm.getXpos());
            int yDif = Math.abs(ghosts[i].getYpos()-pm.getYpos());
            if (xDif < 25 && yDif < 25){
                if (canEat)
                    ghosts[i] = new Ghost(ghosts[i].getGhostType());
                return true;
            }
        }
        return false;
    }

}

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

        int firstIndexRows = (int)(currentY/28) -1 > 0 ? (int)(currentY/28) -1 : 0;
        int lastIndexRows = (int)(currentY/28) +2 < 10 ? (int)(currentY/28) +2 : 10;
        int firstIndexColumns = (int)(currentX/28) -1 > 0 ? (int)(currentX/28) -1 : 0;
        int lastIndexColumns = (int)(currentX/28) +2 < 19 ? (int)(currentX/28) +2 : 19;

        switch (curMovement){
            case UP:
                if (firstIndexColumns+1 <= lastIndexColumns && (firstIndexColumns+1)*28 == currentX){
                    if (gameMap[firstIndexRows][firstIndexColumns+1] == 1){
                        if (firstIndexRows+1 <= lastIndexRows && (firstIndexRows+1)*28 < currentY)
                            return true;
                        return false;
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
                    if (gameMap[firstIndexRows+1][firstIndexColumns] == 1){
                        if (firstIndexColumns+1 <= lastIndexColumns && (firstIndexColumns+1)*28 < currentX)
                            return true;
                        return false;
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
                    gameMap[firstIndexY+1][firstIndexX + 1] = -1;
                    break;
            }
        }

        if ( (pm.getXpos() - 120)%28 == 14){
            foodsToRemove = new ArrayList<Food>();
            for (Food f: foods) {
                if ((f.getXpos() == (pm.getXpos() - 14) || f.getXpos() == (pm.getXpos() + 14)) && f.getYpos() == pm.getYpos()){
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
            switch (gameMap[firstIndexY][firstIndexX+1]){
                case 2:case 3:case 4: case 5:
                    gameMap[firstIndexY][firstIndexX+1] = -1;
                    break;
            }
        }


        return score;
    }

    public boolean doBumpGhosts(){
        if (isMultiplayer){
            return checkPandGhosts(pacmans[0]) || checkPandGhosts(pacmans[1]);
        }
        return checkPandGhosts(pacmans[0]);
    }

    private boolean checkPandGhosts(Pacman pm){
        for (Ghost g:ghosts) {
            int xDif = Math.abs(g.getXpos()-pm.getXpos());
            int yDif = Math.abs(g.getYpos()-pm.getYpos());
            if (xDif < 25 && yDif < 25){
                return true;
            }
        }
        return false;
    }
}

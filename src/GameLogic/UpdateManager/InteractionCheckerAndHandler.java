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

    final static int initialX = 120, initialY = 96;
    static int[][] gameMap;
    ArrayList<Food> foods;
    Pacman[] pacmans;
    Ghost[] ghosts;

    public InteractionCheckerAndHandler(int[][] gameMap, ArrayList<Food> foods, Pacman[] pacmans, Ghost[] ghosts){
        this.foods = foods;
        this.gameMap = gameMap;
        this.ghosts = ghosts;
        this.pacmans = pacmans;
    }

    public static boolean isMoveAllowed(MovingObject movingObject, Movement curMovement){
        int currentX = movingObject.getXpos() - initialX;
        int currentY = movingObject.getYpos() - initialY;

        int firstIndexRows = (int)(currentY/28) -1 > 0 ? (int)(currentY/28) -1 : 0;
        int lastIndexRows = (int)(currentY/28) +2 < 10 ? (int)(currentY/28) +2 : 10;
        int firstIndexColumns = (int)(currentX/28) -1 > 0 ? (int)(currentX/28) -1 : 0;
        int lastIndexColumns = (int)(currentX/28) +2 < 19 ? (int)(currentX/28) +2 : 19;

        switch (curMovement){
            case UP:
                if (firstIndexColumns+1 <= lastIndexColumns && initialX + (firstIndexColumns+1)*28 == currentX){
                    if (gameMap[firstIndexRows][firstIndexColumns+1] == 1){
                        if (firstIndexRows+1 <= lastIndexRows && initialY + (firstIndexRows+1)*28 < currentY)
                            return true;
                        return false;
                    }else{return true;}
                }else{
                    return false;
                }
            case RIGHT:
                if (firstIndexRows+1 <= lastIndexRows && initialY + (firstIndexRows+1)*28 == currentY){
                    if (firstIndexColumns+2 <= lastIndexColumns && gameMap[firstIndexRows+1][firstIndexColumns+2] == 1){
                        return false;
                    }else{
                        if (firstIndexColumns+3 <= lastIndexColumns && gameMap[firstIndexRows+1][firstIndexColumns+3] == 1)
                            if (initialX + (firstIndexColumns+3)*28 < currentX)
                                return true;
                        return false;
                    }
                }else{
                    return false;
                }
            case DOWN:
                if (firstIndexColumns+1 <= lastIndexColumns && initialX + (firstIndexColumns+1)*28 == currentX){
                    if (firstIndexColumns+1 <= lastIndexColumns && firstIndexRows+2 <= lastIndexRows && gameMap[firstIndexRows+2][firstIndexColumns+1] == 1){
                        return false;
                    }else{
                        if (firstIndexRows+3 <= lastIndexRows && gameMap[firstIndexRows+3][firstIndexColumns+1] == 1)
                            if (initialX + (firstIndexRows+3)*28 > currentY)
                                return true;
                        return false;
                    }
                }else{
                    return false;
                }
            case LEFT:
                if (firstIndexRows+1 <= lastIndexRows && initialY + (firstIndexRows+1)*28 == currentY){
                    if (gameMap[firstIndexRows+1][firstIndexColumns] == 1){
                        if (firstIndexColumns+1 <= lastIndexColumns && initialX + (firstIndexColumns+1)*28 < currentX)
                            return true;
                        return false;
                    }else{return true;}
                }else{
                    return false;
                }
        }

        return true;
    }
}

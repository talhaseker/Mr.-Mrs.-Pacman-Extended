package GameLogic.UpdateManager;

import GameLogic.Enums.Movement;
import GameLogic.Enums.PacmanType;
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

    public boolean isMoveAllowed(MovingObject movingObject, Movement curMovement, boolean canPassWall){
//    public boolean isMoveAllowed(MovingObject movingObject, Movement curMovement){
        int currentX = movingObject.getXpos() - initialX;
        int currentY = movingObject.getYpos() - initialY;

        int firstIndexRows = currentY/28 -1 > 0 ? currentY/28 -1 : 0;
        int lastIndexRows = currentY/28 +2 < 10 ? currentY/28 +2 : 10;
        int firstIndexColumns = currentX/28 -1 > 0 ? currentX/28 -1 : 0;
        int lastIndexColumns = currentX/28 +2 < 19 ? currentX/28 +2 : 19;

        switch (curMovement){
            case UP:
                if (firstIndexColumns+1 <= lastIndexColumns && (firstIndexColumns+1)*28 == currentX){
                    if (gameMap[firstIndexRows][firstIndexColumns+1] == 1 || gameMap[firstIndexRows][firstIndexColumns+1] == -2){
                        if (gameMap[firstIndexRows][firstIndexColumns+1] == -2)
                            return firstIndexRows + 1 <= lastIndexRows && (firstIndexRows + 1) * 28 < currentY;
                        if (gameMap[firstIndexRows][firstIndexColumns+1] == 1)
                            return (firstIndexRows + 1 <= lastIndexRows && (firstIndexRows + 1) * 28 < currentY) || canPassWall;
                    }else{return true;}
                }else{
                    return (firstIndexRows != 0) && canPassWall;
                }
            case RIGHT:
                if (firstIndexRows+1 <= lastIndexRows && (firstIndexRows+1)*28 == currentY){
                    if (firstIndexColumns+2 <= lastIndexColumns && (gameMap[firstIndexRows+1][firstIndexColumns+2] == 1 || gameMap[firstIndexRows+1][firstIndexColumns+2] == -2)){
                        if (gameMap[firstIndexRows+1][firstIndexColumns+2] == -2)
                            return false;
                        if (gameMap[firstIndexRows+1][firstIndexColumns+2] == 1)
                            return canPassWall;
                    }else{
                        if (firstIndexColumns+3 <= lastIndexColumns && gameMap[firstIndexRows+1][firstIndexColumns+3] == 1){
                            if ((firstIndexColumns+3)*28 > currentX || canPassWall){
                                return true;}
                        }
                        if (firstIndexColumns+3 <= lastIndexColumns && gameMap[firstIndexRows+1][firstIndexColumns+3] == -2){
                            if ((firstIndexColumns+3)*28 > currentX){
                                return true;}
                        }

                        return true;
                    }
                }else{
                    return ((firstIndexColumns + 2 <= lastIndexColumns) && (firstIndexColumns +2 != 19) && canPassWall) ;
                }
            case DOWN:
                if (firstIndexColumns+1 <= lastIndexColumns && (firstIndexColumns+1)*28 == currentX){
                    if (firstIndexRows+2 <= lastIndexRows && (gameMap[firstIndexRows+2][firstIndexColumns+1] == 1 || gameMap[firstIndexRows+2][firstIndexColumns+1] == 0 || gameMap[firstIndexRows+2][firstIndexColumns+1] == -2)){
                        if (gameMap[firstIndexRows+2][firstIndexColumns+1] == 1 || gameMap[firstIndexRows+2][firstIndexColumns+1] == 0)
                            return canPassWall;
                        else
                            return false;
                    }else{
                        if (firstIndexRows+3 <= lastIndexRows && (gameMap[firstIndexRows+3][firstIndexColumns+1] == 1 || gameMap[firstIndexRows+3][firstIndexColumns+1] == 0 || gameMap[firstIndexRows+3][firstIndexColumns+1] == -2))
                            if (gameMap[firstIndexRows+3][firstIndexColumns+1] == -2)
                                return (firstIndexRows+3)*28 > currentY;
                            else if (gameMap[firstIndexRows+3][firstIndexColumns+1] == 1 || gameMap[firstIndexRows+3][firstIndexColumns+1] == 0)
                                return (firstIndexRows+3)*28 > currentY || canPassWall;
                        return true;
                    }
                }else{
                    return ((firstIndexRows + 2 <= lastIndexRows) && (firstIndexRows +2 != 10) && canPassWall);
                }
            case LEFT:
                if (firstIndexRows+1 <= lastIndexRows && (firstIndexRows+1)*28 == currentY){
                    if (gameMap[firstIndexRows+1][firstIndexColumns] == 1 || gameMap[firstIndexRows+1][firstIndexColumns] == -2){
                        if (gameMap[firstIndexRows+1][firstIndexColumns] == -2)
                            return firstIndexColumns + 1 <= lastIndexColumns && (firstIndexColumns + 1) * 28 < currentX;
                        if (gameMap[firstIndexRows+1][firstIndexColumns] == 1)
                            return (firstIndexColumns + 1 <= lastIndexColumns && (firstIndexColumns + 1) * 28 < currentX) || canPassWall;
                    }else{return true;}
                }else{
                    return (firstIndexColumns != 0) && canPassWall;
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
        int score = 0;
        int firstIndexY = (pm.getYpos() - 96)/28;
        int firstIndexX = (pm.getXpos() - 120)/28;
        ArrayList<Food> foodsToRemove;

        if ((pm.getYpos() - 96)%28 == 14){
            foodsToRemove = new ArrayList<Food>();
            for (Food f: foods) {
                int intervalError = 15;
                boolean isfoodeaten = false;
                for (int yInterval = 14 - intervalError; yInterval < 14 + intervalError + 1 && !isfoodeaten; yInterval++) {
                    for (int xInterval = - intervalError ; xInterval < intervalError + 1 && !isfoodeaten; xInterval++) {
                        if ((f.getYpos() == (pm.getYpos() - yInterval) || f.getYpos() == (pm.getYpos() + yInterval)) && f.getXpos() == pm.getXpos() + xInterval) {
                            foodsToRemove.add(f);
                            pm.eatFood(f);
                            isfoodeaten = true;
                        }
                    }
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

    public boolean doBumpGhosts(int index, boolean canEat){
//        if (isMultiplayer){
//            return checkPandGhosts(pacmans[0], canEat) || checkPandGhosts(pacmans[1], canEat);
//        }
        return checkPandGhosts(pacmans[index], canEat);
    }

    private boolean checkPandGhosts(Pacman pm, boolean canEat){
        for (int i = 0; i < ghosts.length; i++) {
            int xDif = Math.abs(ghosts[i].getXpos()-pm.getXpos());
            int yDif = Math.abs(ghosts[i].getYpos()-pm.getYpos());
            if (xDif < 25 && yDif < 25){
                if (canEat) {
                    if (pm.isGhostEaten(i)) {
                        if (!ghosts[i].isAttacking) ghosts[i].flipAttack();
                        pm.setDieByEatenGhost(true);

                    }

                    else{
                        ghosts[i] = new Ghost(ghosts[i].getGhostType());
                        pm.setGhostEaten(i,true);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void savePacmanFromWalls(Pacman pm){
        int row = (pm.getYpos() - initialY)/28;
        int column = (pm.getXpos() - initialX)/28;
        pm.setCanPassWall(false);
        if (!isWall(row, column)){
            pm.setXpos(column*28 + initialX);
            pm.setYpos(row*28 + initialY);
            return;
        }else {
            for(int i = -1; i<2; i++){
                for (int j = -1; j<2; j++){
                    if ((0 <= row+i && row+i<=10) && (0 <= column+j && column+j<=19) && isWall(row+i, column+j)){
                        pm.setXpos((column + i)*28 + initialX);
                        pm.setYpos((row+j)*28 + initialY);
                        return;
                    }
                }
            }
            if (pm.getPacmanType() == PacmanType.MRPACMAN){
                pm.setXpos(372);
                pm.setYpos(348);
            }else {
                pm.setXpos(316);
                pm.setYpos(348);
            }
        }

    }

    private boolean isWall(int row, int column){
        return (gameMap[row][column] == 1 || gameMap[row][column] == -2 || gameMap[row][column] == 0);
    }

}

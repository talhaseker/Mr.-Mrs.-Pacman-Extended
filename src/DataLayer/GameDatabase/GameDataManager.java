/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.GameDatabase;

import DataLayer.MapDatabase.MapDataManager;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import java.io.*;

/**
 *
 * @author mbpro
 */
public class GameDataManager {

    GameData gd = new GameData();

    public void setGameData(int score, int level, int numbPlayer, int livesLeft, Pacman[] pacmans, Ghost[] ghosts, int[][] mapTable, int[][] mapRestoreTable){
        MapDataManager mdManager = new MapDataManager();
        gd.setLevel(level);
        gd.setLivesLeft(livesLeft);
        gd.setMapData(mdManager.setMapData(pacmans, ghosts, mapTable, mapRestoreTable));
        gd.setScore(score);
        gd.setNumPlayer(numbPlayer);
    }

    public void saveGameData(String name, int score, int level, int numbPlayer, int livesLeft, Pacman[] pacmans, Ghost[] ghosts, int[][] mapTable, int[][] mapRestoreTable){
        setGameData(score, level, numbPlayer,livesLeft,pacmans, ghosts, mapTable, mapRestoreTable);
        File gameFile = new File(name);
        try {
            FileOutputStream fileOutput = new FileOutputStream(gameFile);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(gd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameData loadGame(String name){
        GameData gameData = new GameData();
        File gameFile = new File(name);
        try {
            FileInputStream fileInput = new FileInputStream(gameFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInput);
            gameData = (GameData) objectInputStream.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameData;
    }
}

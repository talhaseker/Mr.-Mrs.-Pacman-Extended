/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.GameDatabase;

import DataLayer.MapDatabase.MapData;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author mbpro
 */
public class GameData implements Serializable{

    private MapData mapData;
    private int score;
    private int level;
    private int numPlayer;
    private int livesLeft;

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(mapData);
        stream.writeInt(score);
        stream.writeInt(level);
        stream.writeInt(numPlayer);
        stream.writeInt(livesLeft);
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        mapData = (MapData) stream.readObject();
        score = stream.readInt();
        level = stream.readInt();
        numPlayer = stream.readInt();
        livesLeft = stream.readInt();
    }

    public MapData getMapData() {
        return mapData;
    }

    public void setMapData(MapData mapData) {
        this.mapData = mapData;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }
}

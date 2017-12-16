/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.MapDatabase;

import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Ecem Ilgun (Şevval Ekici will add her name in the upcoming days)
 */
public class MapData implements Serializable{
    
    //Properties
    private Pacman[] pacmans;
    private Ghost ghosts[];
    private int[][] mapTable;

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(pacmans);
        stream.writeObject(ghosts);
        stream.writeObject(mapTable);
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        pacmans = (Pacman[]) stream.readObject();
        ghosts = (Ghost[]) stream.readObject();
        mapTable = (int[][]) stream.readObject();
    }

    public Pacman[] getPacmans() {
        return pacmans;
    }

    public void setPacmans(Pacman[] pacmans) {
        this.pacmans = pacmans;
    }

    public Ghost[] getGhosts() {
        return ghosts;
    }

    public void setGhosts(Ghost[] ghosts) {
        this.ghosts = ghosts;
    }

    public int[][] getMapTable() {
        return mapTable;
    }

    public void setMapTable(int[][] mapTable) {
        this.mapTable = mapTable;
    }
}

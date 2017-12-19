/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.MapDatabase;

import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

/**
 *
 * @author mbpro
 */
public class MapDataManager {

    public MapData setMapData(Pacman[] pacmans, Ghost[] ghosts, int[][] mapTable, int[][] mapRestoreTable){
        MapData md = new MapData();
        md.setGhosts(ghosts);
        md.setMapTable(mapTable);
        md.setPacmans(pacmans);
        md.setMapRestoreTable(mapRestoreTable);
        return md;
    }
}

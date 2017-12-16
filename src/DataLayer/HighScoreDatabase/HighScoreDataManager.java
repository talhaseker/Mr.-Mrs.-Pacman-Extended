/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.HighScoreDatabase;


import DataLayer.HighScoreDatabase.HighScoreData;


/**
 *
 * @author sevval ekici
 */
public class HighScoreDataManager {

    int score;

    public static HighScoreData setHighScoreData(String name, int score){
        HighScoreData hsd = new HighScoreData();
        hsd.setScore(score);
        return hsd;
    }

    }

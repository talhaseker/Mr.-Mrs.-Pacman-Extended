/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.HighScoreDatabase;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 *
 * @author sevval ekici
 */
public class HighScoreDataManager {

    private HighScoreData[] hsdArr = new HighScoreData[10];

    public HighScoreDataManager(){
        for (int i=0;i<10;i++)
            hsdArr[i] = new HighScoreData();

    }

    public static HighScoreData setHighScoreData(String name, int score){
        HighScoreData hsd = new HighScoreData();
        hsd.setScore(score);
        hsd.setName(name);
        return hsd;
    }
    public void writeHighScore(String name, int score){
        this.getHighScores();
        HighScoreData hsd = setHighScoreData(name, score);
        ArrayList<HighScoreData> hsdList = new ArrayList<>(Arrays.asList(hsdArr));
        hsdList.add(hsd);
        hsdList.sort(Comparator.comparing(HighScoreData::getScore));

        for (int i=10;i>0;i--){
            hsdArr[10-i] = hsdList.get(i);
        }

        File hsdFile = new File("high.scores");
        try {
            FileOutputStream fileOutput = new FileOutputStream(hsdFile);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(hsdArr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HighScoreData[] getHighScores(){
        File scoresFile = new File("high.scores");
        try {
            FileInputStream fileInput = new FileInputStream(scoresFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInput);
            this.hsdArr = (HighScoreData[]) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.hsdArr;
    }
}

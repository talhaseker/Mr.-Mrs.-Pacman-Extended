/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.HighScoreDatabase;

import java.io.*;
import java.util.Arrays;

import DataLayer.GameDatabase.GameDataManager;
/**
 *
 * @author sevval ekici
 */
public class HighScoreData implements Serializable {
    
    private int highScores[]= new int[10];
    private int score;

    public void writeHighScores(){
        try {
            FileWriter fw = new FileWriter("highscores.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            for (int i= 0; i<10; i++){
                out.println(""+ highScores[i]);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readHighScores() throws IOException {
        try{
        BufferedReader in = new BufferedReader(new FileReader("/Users/mbpro/Documents/GitHub/Mr.-Mrs.-Pacman-Extended/out/production/Mr.-Mrs.-Pacman-Extended/DataLayer/HighScoreDatabase/highscores.txt"));
        String str="";
        int i=0;
        while((str = in.readLine()) != null){
            highScores[i++]= Integer.parseInt(str);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setHighScores(){
        boolean isChanged = false;
        if(highScores[9]==0){
            for(int i=0; i<10; i++){
                if(highScores[i]==0) {
                    highScores[i] = score;
                    isChanged = true;
                    break;
                }
            }
        }
        else{
            for(int i=0;i<10;i++){
                if (highScores[i]<score){
                    highScores[i]= score;
                    isChanged= true;
                    break;
                }
            }
        }
        if(isChanged){
            Arrays.sort(highScores);

        }

    }
    public int[] getHighScores() {
        return highScores;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}

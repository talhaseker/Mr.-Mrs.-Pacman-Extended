/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.HighScoreDatabase;

import java.io.IOException;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

/**
 *
 * @author sevval ekici
 */
public class HighScoreData implements Serializable {
    
    private int highScores[];

    private void writehighScores(){
        try {
            fw = new FileWriter("highscores.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            for (int i= 0; i<10; i++){
                out.println(""+ highScores[i]);
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private void readHighScores(){


    }

    public int[] getHighScores() {
        return highScores;
    }

    public void setHighScores(int[] highScores) {
        this.highScores = highScores;
    }



    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.HighScoreDatabase;

import java.io.IOException;
import java.io.Serializable;
/**
 *
 * @author sevval ekici
 */
public class HighScoreData implements Serializable {
    
//    private int highScores[]= new int[10];
    private String name;
    private int score;

    public HighScoreData(){
        this.name = "---";
        this.score=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(name);
        stream.writeInt(score);
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        name = (String) stream.readObject();
        score = stream.readInt();
    }

    @Override
    public String toString() {
        return "HighScoreData{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

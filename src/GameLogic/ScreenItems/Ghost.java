/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.ScreenItems;

// import java.awt.Point;

import GameLogic.AnimationManager.Sprite;
import GameLogic.Enums.GhostAnimationType;
import GameLogic.Enums.GhostType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

import static GameLogic.Constants.ATTACK;
import static GameLogic.Constants.SCATTER;

/** Represent ghost's data
 * @author SEVVAL EKICI
 * @version 1.8
 * @since 1.0
 * @see PacmanObject
 */
public class Ghost extends MovingObject implements Serializable {
    //Properties
    private GhostType type;
    private GhostAnimationType currentAnimationType;
    private int points;
    private boolean isAlive = false; //I THINK WE SHOULD CONTROL THIS THAT WAY - Şevval
                                     // I also think we do not have to use GhostAnimationType as enum
                                     // since it can only be alive or dead. However, PacmanAnimationType
                                     // should be used. I don't know if using different approaches in
                                     // pacman and ghost is a good design choice - Ecem

    public int countdownTimer = 0;
    public boolean isAttacking = false;
    private boolean scatter;
//    final int initialOutOfCageX = 372;
    final int initialOutOfCageY = 208;

    //Constructor
	public Ghost (GhostType type) {
        super();
        super.setSize(28,28);

        switch (type){
            case INKY:
                super.setSpeed(2);
                super.setImage("icons/inky1");
                break;
            case PINKY:
                super.setSpeed(2);
                super.setImage("icons/pinky1");
                break;
            case CLYDE:
                super.setSpeed(2);
                super.setImage("icons/clyde1");
                break;
            case BLINKY:
                super.setSpeed(2);
                super.setImage("icons/blinky1");
                break;
            default:
                break;
        }

	    this.type = type;
        startPosition();
        isAlive = true;
	}
        
    //Methods

    /** This method determines the place of the ghost according to their type
     */
    public void startPosition() {
        if (this.type == GhostType.BLINKY){
            super.setXpos(344);
            super.setYpos(236);
        }
        if(this.type == GhostType.INKY){
            super.setXpos(372);
            super.setYpos(236);
        }
        if(this.type == GhostType.PINKY){
            super.setXpos(400);
            super.setYpos(236);
        }
        if(this.type == GhostType.CLYDE){
            super.setXpos(428);
            super.setYpos(236);
        }
	}

	/**
	 * Resets ghost for next level
	 */
	public void setForNextLevel(){
        String ghostName = type.name().toLowerCase();

        //Fix for Turkish keyboard
        for (int i = 0; i < ghostName.length(); i++)
            if (ghostName.charAt(i) == 'ı')
                ghostName = ghostName.substring(0,i) + 'i' + ghostName.substring(i+1);

        super.setImage("icons/" + ghostName +"1");

        startPosition();
        isAlive = true;
	}

    /**
     * @return the points that user gains when pacman eats the ghost
     */
	public int getValue() {
		return points;
	}
    public GhostType getGhostType() {
        return type;
    }
	
	public void chase()
	{
	    if (isAlive) return;
		    //TODO
	}
        
    public void die(){
        isAlive = false;
        }
        
    public boolean isScattered(){
        return scatter;
    }

    public void scatter(){
        scatter = true;
        super.setImage(Sprite.loadSprite("icons/blue1"));
    }

    public void unScatter() { //normal hali
        scatter = false;
        String ghostType = this.type.name();
        super.setImage(Sprite.loadSprite("icons/" + ghostType + "1"));
    }

    public void respawnInCage() {
        this.startPosition();
        this.unScatter();
    }

    public Point getInitialOutOfCagePos() {
            return new Point(this.getXpos(),initialOutOfCageY);
    }

    public void flipAttack(){
        if(isAttacking){
            countdownTimer = SCATTER;
        } else{
            countdownTimer = ATTACK;
        }
        isAttacking = !isAttacking;
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(type);
        stream.writeBoolean(isAlive);
        stream.writeInt(countdownTimer);
        stream.writeBoolean(isAttacking);
        stream.writeBoolean(scatter);
        stream.writeInt(super.speed);
        stream.writeInt(super.Xpos);
        stream.writeInt(super.Ypos);
        stream.writeInt(super.width);
        stream.writeInt(super.height);
        ImageIO.write(imageIcon, "png", stream);
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        type = (GhostType) stream.readObject();
        isAlive = stream.readBoolean();
        countdownTimer = stream.readInt();
        isAttacking = stream.readBoolean();
        scatter = stream.readBoolean();
        super.speed = stream.readInt();
        super.Xpos = stream.readInt();
        super.Ypos = stream.readInt();
        super.width = stream.readInt();
        super.height = stream.readInt();
        super.imageIcon = ImageIO.read(stream);
    }

    @Override
    public String toString() {
        return "Ghost{" +
                "type=" + type +
                ", currentAnimationType=" + currentAnimationType +
                ", points=" + points +
                ", isAlive=" + isAlive +
                ", countdownTimer=" + countdownTimer +
                ", isAttacking=" + isAttacking +
                ", scatter=" + scatter +
                ", initialOutOfCageY=" + initialOutOfCageY +
                '}';
    }
}
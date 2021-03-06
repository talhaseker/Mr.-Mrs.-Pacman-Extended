package GameLogic.ScreenItems;

import GameLogic.Enums.ShieldType;

import java.io.IOException;
import java.io.Serializable;

/** Shield (upgrade) object for Pacman
 * @author Ecem Ilgun
 * @version 1.9
 * @since 1.0
 */
public class Shield implements Serializable{
    private ShieldType type;
    private int effectTime;
    private int cost;

    public Shield(ShieldType type){
        this.type = type;
        switch (type){
            case COPPER:
                effectTime = 1000;
                cost = 3000;
                break;
            case GOLD:
                effectTime = 1000;
                cost = 7000;
                break;
            default: //SILVER
                effectTime = 1333;
                cost = 5000;
                break;
        }
    }


    public ShieldType getType() {
        return type;
    }

    public void setType(ShieldType type) {
        this.type = type;
    }

    public int getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(int effectTime) {
        this.effectTime = effectTime;
    }

    public int getCost(){return cost;}

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(type);
        stream.writeInt(effectTime);
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        type = (ShieldType) stream.readObject();
        effectTime = stream.readInt();
    }
}

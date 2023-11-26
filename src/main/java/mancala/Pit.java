package mancala;
import java.io.Serializable;

public class Pit implements Serializable, Countable{

    private int stoneCount;
    private static final long serialVersionUID = -8581857526535546531L;

    public Pit(){
        stoneCount = 0;
    }
    @Override
    public void addStone(){
        stoneCount++;
    }
    @Override
    public void addStones(final int num){
        stoneCount += num;
    }
    @Override
    public int getStoneCount(){
        return stoneCount;
    }
    @Override
    public int removeStones(){
        final int totalStones = stoneCount;
        stoneCount = 0;
        return totalStones;
    }
}
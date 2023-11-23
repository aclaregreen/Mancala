package mancala;
import java.io.Serializable;

public class Pit implements Serializable, Countable{

    private int stoneCount;

    public Pit(){
        stoneCount = 0;
    }
    public void addStone(){
        stoneCount++;
    }
    public void addStones(int num){
        stoneCount += num;
    }
    public int getStoneCount(){
        return stoneCount;
    }
    public int removeStones(){
        final int totalStones = stoneCount;
        stoneCount = 0;
        return totalStones;
    }
}
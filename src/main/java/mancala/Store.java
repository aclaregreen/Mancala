package mancala;
import java.io.Serializable;

public class Store implements Serializable, Countable{

    private Player owner;
    private int stones;
    public Store(){

    }
    public Store(final Player player){
        this.owner = player;
        stones = 0;
    }
    public void setOwner(final Player player){
        this.owner = player;
    }
    public Player getOwner(){
        return owner;
    }
    public void addStone(){
        stones++;
    }
    public void addStones(final int amount){
        stones += amount;
    }
    public int removeStones(){
        final int totalStones = stones;
        stones = 0;
        return totalStones;
    }
    public int getStoneCount(){
        return stones;
    }
}
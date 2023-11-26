package mancala;
import java.io.Serializable;

public class Store implements Serializable, Countable{

    private Player owner;
    private int stones;
    private static final long serialVersionUID = -6319809619248777988L;
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
    @Override
    public void addStone(){
        stones++;
    }
    @Override
    public void addStones(final int amount){
        stones += amount;
    }
    @Override
    public int removeStones(){
        final int totalStones = stones;
        stones = 0;
        return totalStones;
    }
    @Override
    public int getStoneCount(){
        return stones;
    }
}
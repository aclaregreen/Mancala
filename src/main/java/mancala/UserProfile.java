package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable{
    private String userName;
    private int numKalah;
    private int numAyo;
    private int kalahWins;
    private int ayoWins;
    private static final long serialVersionUID = -3995813209754125734L;

    public UserProfile(){
        numKalah = 0;
        numAyo = 0;
        kalahWins = 0;
        ayoWins = 0;
    }
    public void setUserName(final String name){
        this.userName = name;
    }
    public String getUserName(){
        return userName;
    }
    public void addKalahWin(){
        kalahWins += 1;
    }
    public void addAyoWin(){
        ayoWins += 1;
    }
    public void addKalah(){
        numKalah += 1;
    }
    public void addAyo(){
        numAyo += 1;
    }
    public int getKalahPlayed(){
        return numKalah;
    }
    public int getAyoPlayed(){
        return numAyo;
    }
    public int getKalahWon(){
        return kalahWins;
    }
    public int getAyoWon(){
        return ayoWins;
    }
    
}
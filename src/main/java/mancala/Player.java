package mancala;
import java.io.Serializable;

public class Player implements Serializable{

    private String user;
    private Store playerStore;
    private UserProfile profile;
    private static final long serialVersionUID = -8059249044666139320L;

    public Player(){
    }
    public Player(final String name, UserProfile userProfile){
        this.user = name;
        this.profile = userProfile;
    }
    public Player(final String name, final Store store){
        this.user = name;
        this.playerStore = store;
    }
    public void setProfile(UserProfile userProfile){
        this.profile = userProfile;
    }
    public UserProfile getProfile(){
        return profile;
    }
    public String getName(){
        return user;
    }
    public void setStore(final Store store){
        this.playerStore = store;
    }
    public Store getStore(){
        return playerStore;
    }
    public int getStoreCount(){
            return playerStore.getStoneCount();
    }
    public void setName(final String name){
        this.user = name;
    }
}
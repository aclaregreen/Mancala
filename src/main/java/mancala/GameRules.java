package mancala;
import java.io.Serializable;
/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable{
    private final MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)
    private static final long serialVersionUID = -3186782306362864552L;
    private Store playerOneStore = new Store();
    private Store playerTwoStore = new Store();
    //private GameRules kalahGame = new KalahRules();
    private boolean extraTurn;

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure(4);
        //game = new MancalaGame();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    public MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(final int pitNum) throws PitNotFoundException {
        boolean isEmpty;
        //validatePit(pitNum-1);
        if (countSide(pitNum) == 0){
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return isEmpty;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.

        playerOneStore.setOwner(one);
        playerTwoStore.setOwner(two);

        one.setStore(playerOneStore);
        two.setStore(playerTwoStore);

        gameBoard.setStore(playerOneStore,1);
        gameBoard.setStore(playerTwoStore,2);

        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }
    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    int whichPlayer(final int pitNum){
    int player;
        if (pitNum >= 1 && pitNum <= 6){
            player = 1;
        } else {
            player = 2;
        }
        return player;
    }
    public int countSide(final int pitNum){
        int total = 0;
        int start;
        int end;
        if (pitNum >= 1 && pitNum <= 6){
            start = 1;
            end = 7;
        } else {
            start = 7;
            end = 13;
        }
        for (int i = start; i < end; i++){
            total += gameBoard.getNumStones(i);
        }
        return total;
    }
    public void setExtraTurn(final boolean bonus){
        this.extraTurn = bonus;
    }
    public boolean isExtraTurn(){
        return extraTurn;
    }
    int getPitNum(final int currentIndex){
        int pitNum = currentIndex;
        if (currentIndex <= 5){
            pitNum++;
        }
        return pitNum;
    }
    @Override
    public String toString() {
        // Implement toString() method logic here.
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < 14; i++){
            if (i != 6 && i!= 13){
                board.append(getDataStructure().getNumStones(i)).append("\t");
            } else {
                board.append("\n");
            }
        }
        for (int i = 1; i < 3; i++){
            board.append("Store: " + getDataStructure().getStoreCount(i+1)).append("\n");
        }
        return board.toString();
    }
}

package mancala;
import java.util.ArrayList;
import java.io.Serializable;
//import java.util.List;

public class MancalaGame implements Serializable{

    //private static long serialVersionUID = 1L;
    private static final long serialVersionUID = -2713556366135635498L;
    private GameRules gameBoard;
    final private ArrayList<Player> players;
    private Player currentPlayer;

    public MancalaGame(){
        //gameBoard = new KalahRules();
        players = new ArrayList<>();
        //currentPlayer = null;
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public int getNumStones(final int pitNum) throws PitNotFoundException {
        if (pitNum > 12 || pitNum < 1){
            throw new PitNotFoundException("Pit number is invalid: " + pitNum);
        }
        return gameBoard.getNumStones(pitNum);
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public int getStoreCount(final Player player) throws NoSuchPlayerException {
        if (!players.contains(player)){
            throw new NoSuchPlayerException("Player not found: " + player);
        }
        return player.getStoreCount();
    }
    public Player getWinner() throws GameNotOverException {
        if (isGameOver()){
            int playerOneTotal;
            int playerTwoTotal;
            for (int i = 0; i < 6; i++){
                players.get(0).getStore().addStones(gameBoard.getDataStructure().removeStones(i+1));
            }
            for (int i = 6; i < 12; i++){
                players.get(1).getStore().addStones(gameBoard.getDataStructure().removeStones(i));
            }
            playerOneTotal = players.get(0).getStoreCount();
            playerTwoTotal = players.get(1).getStoreCount();
            if (playerOneTotal > playerTwoTotal){
                return players.get(0);
            } else if (playerOneTotal < playerTwoTotal){
                return players.get(1);
            } else {
                return null;
            }
        } else {
             throw new GameNotOverException("The game is not over yet");
        }
    }
    public boolean isGameOver(){
        try{
            return gameBoard.isSideEmpty(1) || gameBoard.isSideEmpty(7);
        } catch (PitNotFoundException e) {
            // Handle the exception as needed
            e.printStackTrace(); // or log the error
            return false; // Or return a default value
        }
    }
    public int move(final int startPit) throws InvalidMoveException {
        if (startPit >= 1 && startPit <= 6 && currentPlayer.equals(players.get(1))
            || startPit >= 7 && startPit <= 12 && currentPlayer.equals(players.get(0))) {
                throw new InvalidMoveException("Invalid move");
        }
        int totalStones;
        totalStones = gameBoard.countSide(startPit);
        int player;
        if (startPit >= 1 && startPit <= 6){
            player = 1;
        } else {
            player = 2;
        }
            gameBoard.moveStones(startPit, player);
        return totalStones;
    }
    public void setBoard(final GameRules theBoard){
        this.gameBoard = theBoard;
    }
    public GameRules getBoard(){
        return gameBoard;
    }
    public void setCurrentPlayer(final Player player){
        this.currentPlayer = player;
    }
    public void setPlayers(final Player onePlayer, final Player twoPlayer){
        players.clear();
        this.players.add(onePlayer);
        this.players.add(twoPlayer);
    }
    public void startNewGame(){
        gameBoard.resetBoard();
        currentPlayer = players.get(0);
    }
    @Override
    public String toString(){
        return "Mancala game:\n" + gameBoard.toString();
    }
}
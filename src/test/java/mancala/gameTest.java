// package mancala;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.Test;
// import java.io.IOException;
// import java.io.Serializable;

// public class gameTest {

//     @Test
//     public void testSaveAndLoad() {
//         //private Saver saver;
//         Saver saver = new Saver();
//         // Create an instance of the game
//         MancalaGame originalGame = new MancalaGame();

//         Player player1 = new Player("Player 1");
//         Player player2 = new Player("Player 2");
//         UserProfile profile = new UserProfile();
//         //Saver saver = new Saver();

//         originalGame.getBoard().setUpStores();
//         originalGame.getBoard().setUpPits();
//         originalGame.setPlayers(player1, player2);
//         originalGame.setBoard(originalGame.getBoard());
//         originalGame.setCurrentPlayer(player1);
//         originalGame.startNewGame();
//         originalGame.getBoard().initializeBoard();
//         originalGame.getBoard().setUpStores();
//         originalGame.getBoard().registerPlayers(player1, player2);

//         originalGame.getBoard().getStores().get(0).setOwner(player1);
//         originalGame.getBoard().getStores().get(1).setOwner(player2);
        
//         // Perform some actions in the game (modify its state)

//         try {
//             originalGame.move(1);
//         } catch (InvalidMoveException e){
//             e.printStackTrace();
//         }
//         try {
//             originalGame.move(8);
//         } catch (InvalidMoveException e){
//             e.printStackTrace();
//         }
//         try {
//             int stones = originalGame.getBoard().getNumStones(1);
//             assertEquals(0, stones);
//         } catch (PitNotFoundException e){
//             e.printStackTrace();
//         }
        
//         // Save the game to a file
//         //Saver saver = new Saver();
//         String saveFilePath = "Test.txt";
//         try {
//             saver.saveObject(originalGame, saveFilePath);
//         } catch (IOException e){
//             e.printStackTrace();
//         }

//         try {
//             MancalaGame loadedGame = (MancalaGame) saver.loadObject(saveFilePath);
//              //loadedGame.serialVersionUID(loadedObject);
//              System.out.println("GDDDD: " + loadedGame);
//              assertEquals(originalGame.getClass(), loadedGame.getClass(), "Unexpected class type");

//             // Check if the content of the loaded object is as expected
//             assertEquals(originalGame.getBoard().getNumStones(1), loadedGame.getBoard().getNumStones(1), "Object content mismatch");
//             //loadedGame = saver.loadObject(saveFilePath);
//         } catch (IOException e){
//             e.printStackTrace();
//         } catch (PitNotFoundException e){
//             e.printStackTrace();
//         }
       
//     }
// }

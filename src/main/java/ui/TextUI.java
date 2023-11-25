package ui;
import javax.swing.*;

import mancala.AyoRules;
import mancala.GameRules;
import mancala.InvalidMoveException;
import mancala.KalahRules;
import mancala.MancalaDataStructure;
import mancala.MancalaGame;
import mancala.Player;
import mancala.Saver;
import mancala.UserProfile;

import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Arrays;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextUI extends JFrame {
    private JButton[][] buttons; // Declare the array of buttons
    private JButton[]   files;
    private JFrame frame;
    private JPanel panel;
    private GameRules rules;
    private MancalaGame game;
    private Player player1;
    private Player player2;
    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayer = 1;
    private MancalaDataStructure data;
    private Saver saver = new Saver();
    private ArrayList<String> saveFiles = new ArrayList<>();
    private ArrayList<String> profileFiles = new ArrayList<>();
    private String file;

    public TextUI() {
        loadSaveFiles();
        loadProfileFiles();
        createWindow();
        mainMenuPanel();
    }
    private void createWindow(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mancala");
        frame.pack();
        frame.setSize(600, 400);
        frame.setVisible(true);
        createPanel();
    }
    private void createPanel(){
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100,30,10,30));
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
    }

    private void mainMenuPanel() {
        panel.removeAll();
        players.clear();
        loadSaveFiles();
        loadProfileFiles();
        JButton newGame = new JButton("Start New Game");
        JButton loadGame = new JButton("Load Game");
        newGame.setBounds(225, 150, 150, 30);
        loadGame.setBounds(225, 200, 150, 30);
        panel.add(newGame);
        panel.add(loadGame);
        newGame.addActionListener(e -> setUpPlayers());
        loadGame.addActionListener(e -> loadScreen());
        panel.revalidate();
        panel.repaint();
    }
    private void setUpPlayers(){
        panel.removeAll();
        JButton newPlayer = new JButton("New Player");
        JButton loadPlayer = new JButton("Load Player");
        newPlayer.setBounds(225, 150, 150, 30);
        loadPlayer.setBounds(225, 200, 150, 30);
        newPlayer.addActionListener(e -> newProfile());
        loadPlayer.addActionListener(e -> loadProfilesPanel());
        panel.add(newPlayer);
        panel.add(loadPlayer);
        panel.revalidate();
        panel.repaint();
    }
    private void newProfile(){
        panel.removeAll();
        JLabel enterName = new JLabel("Enter name");
        JTextField nameField = new JTextField();
        enterName.setBounds(225, 150, 150, 30);
        nameField.setBounds(225, 200, 150, 30);
        panel.add(enterName);
        panel.add(nameField);
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This method is called when Enter is pressed
                String userInput = nameField.getText();
                UserProfile profile = new UserProfile();
                Player player = new Player(userInput, profile);
                players.add(player);
                nameField.setText(""); // Clear the text field after processing
                if (players.size() == 2){
                    startNewGame();
                } else {
                    setUpPlayers();
                }
            }
        });
        panel.revalidate();
        panel.repaint();
    }
    private void saveProfiles(ArrayList<Player> players){
        try {
            for (int i = 0; i < players.size(); i++){
                file = "players/" + players.get(i).getName();
                saver.saveObject(players.get(i), file);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadProfilesPanel(){
        int x = 10;
        int y = 10;
        if (profileFiles.isEmpty()){
            return;
        }
        panel.removeAll();
        JButton backButton = new JButton("Back");
        backButton.setBounds(500, 25, 75, 30);
        backButton.addActionListener(e -> mainMenuPanel());
        panel.add(backButton);
        files = new JButton [profileFiles.size()];
        for (int i = 0; i < profileFiles.size(); i++){
            final int index = i;
            files[i] = new JButton(profileFiles.get(i));
            files[i].setBounds(x, y, 100, 30);
            files[i].addActionListener(e -> loadProfile(index));
            panel.add(files[i]);
            y += 40;
            if (y >= 350){
                x += 110;
                y = 10;
            }
        }
        panel.revalidate();
        panel.repaint();
    }
    private void loadProfile(int index){
        file = "players/" + profileFiles.get(index);
        try {
            Player player = new Player();
            player = (Player) saver.loadObject(file);
            players.add(player);
            if (players.size() == 2){
                startNewGame();
            } else {
                setUpPlayers();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadProfileFiles(){
        File playersFolder = new File("assets/players/");
        if (playersFolder.exists() && playersFolder.isDirectory()){
            File[] playerFiles = playersFolder.listFiles();
            if (playerFiles != null){
                for (File fname : playerFiles){
                    profileFiles.add(fname.getName());
                }
            }
        } else {
            System.err.println("Folder not found");
        }
    }
    private void startNewGame() {
        saveProfiles(players);
        SwingUtilities.invokeLater(() -> {
        panel.removeAll();
        JButton ayo = new JButton("Ayo Rules");
        JButton kalah = new JButton("Kalah Rules");
        ayo.setBounds(225, 150, 150, 30);
        kalah.setBounds(225, 200, 150, 30);
        ayo.addActionListener(e -> newAyoGame());
        kalah.addActionListener(e -> newKalahGame());
        panel.add(ayo);
        panel.add(kalah);
        panel.revalidate();
        panel.repaint();
        });
    }
    private void setNewGame(){
        game = new MancalaGame();
        player1 = players.get(0);
        player2 = players.get(1);
        currentPlayer = 1;

        game.setPlayers(player1, player2);
        game.setBoard(rules);
        game.setCurrentPlayer(player1);
        //game.startNewGame();

        rules.registerPlayers(player1, player2);
        rules.setPlayer(currentPlayer);
        data = rules.getDataStructure();
        displayBoard();
    }
    private void newAyoGame(){
        rules = new AyoRules(); 
        setNewGame();
    }
    private void newKalahGame(){
        rules = new KalahRules();
        setNewGame();
    }
    private void displayBoard(){
        SwingUtilities.invokeLater(() -> {
        int x = 100;
        int y = 150;
        panel.removeAll();
        if (isGameOver()){
            return;
        }
        JLabel playerTurn = new JLabel(game.getCurrentPlayer().getName() + "'s turn");
        playerTurn.setBounds(300, 50, 100, 30);
        panel.add(playerTurn);
        buttons = new JButton[2][6];
        JLabel store1 = new JLabel("" + data.getStoreCount(1));
        JLabel store2 = new JLabel("" + data.getStoreCount(2));
        store1.setBounds(x+455, y-25, 50, 30);
        store2.setBounds(x-30, y-25, 50, 30);
        panel.add(store1);
        panel.add(store2);
        for (int row = 0; row < 2; row++){
            for (int col = 0; col < 6; col++){
                buttons[row][col] = new JButton("" + data.getNumStones(row * 6 + col + 1));
                buttons[row][col].setBounds(x, y, 50, 30);
                buttons[row][col].setActionCommand("" + (row * 6 + col + 1));
                buttons[row][col].addActionListener(e -> move(e.getActionCommand()));
                panel.add(buttons[row][col]);
                if (row == 0){
                    x += 75;
                } else if (row == 1){
                    x -= 75;
                }
            }
            x -= 75;
            y -= 50;
        }
        JButton quit = new JButton("Quit");
        JButton save = new JButton("Save ");
        quit.setBounds(250,300,100,30);
        save.setBounds(250,225,100,30);
        quit.addActionListener(e -> mainMenuPanel());
        save.addActionListener(e -> saveGame());
        panel.add(quit);
        panel.add(save);
        panel.revalidate();
        panel.repaint();
    });
    }
    private void move(String actionCommand){
        int pitNum = Integer.parseInt(actionCommand);
        try{
            rules.moveStones(pitNum, currentPlayer);
            if (!rules.isExtraTurn()){
                currentPlayer = (currentPlayer == 1 ? 2 : 1);
                rules.setPlayer(currentPlayer);
                game.setCurrentPlayer(currentPlayer == 1 ? player1 : player2);
            }
        } catch (InvalidMoveException e){
            e.printStackTrace();
        }
        displayBoard();
    }
    private boolean isGameOver(){
        if (game.isGameOver()){
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            endOfGame();
            return true;
        }
        return false;
    }
    private void endOfGame(){
        panel.removeAll();
        JButton playAgain = new JButton("Play Again");
        JButton menu = new JButton("Main Menu");
        try {
            JLabel winner;
            if (game.getWinner() == null){
                winner = new JLabel("Its a tie!");
            } else {
                winner = new JLabel(game.getWinner().getName() + " wins!");
            }
            winner.setBounds(225, 50, 150, 30);
            panel.add(winner);
        } catch (Exception e){
            e.printStackTrace();
        }
        playAgain.setBounds(225, 150, 150, 30);
        menu.setBounds(225, 200, 150, 30);
        playAgain.addActionListener(e -> playAgain());        
        menu.addActionListener(e -> mainMenuPanel());
        panel.add(playAgain);
        panel.add(menu);
        panel.revalidate();
        panel.repaint();
    }
    private void playAgain(){
        game = new MancalaGame();
        rules.resetBoard();
        data = rules.getDataStructure();
        game.setPlayers(player1, player2);
        rules.registerPlayers(player1, player2);
        game.setBoard(rules);
        currentPlayer = 1;
        rules.setPlayer(currentPlayer);
        game.setCurrentPlayer(player1);
        //data = rules.getDataStructure();
        displayBoard();
    }
    private void saveGame(){
        //saver = new Saver();
        JTextField textField = new JTextField(20);
        textField.setBounds(225, 260, 150, 30);
        panel.add(textField);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This method is called when Enter is pressed
                String userInput = textField.getText();
                saveFiles.add(userInput); // Add the input to the ArrayList
                file = "games/" + userInput;
                makeSave();
                //resultLabel.setText("Result: " + userInput);
                textField.setText(""); // Clear the text field after processing
            }
        });
        panel.revalidate();
        panel.repaint();
        //saveFilePath = "Test.txt";
    }
    private void makeSave(){
        try {
             saver.saveObject(game, file);
         } catch (Exception e){
             e.printStackTrace();
         }
    }
    private void loadScreen(){
        int x = 10;
        int y = 10;
        if (saveFiles.isEmpty()){
            return;
        }
        panel.removeAll();
        JButton backButton = new JButton("Back");
        backButton.setBounds(500, 25, 75, 30);
        backButton.addActionListener(e -> mainMenuPanel());
        panel.add(backButton);
        files = new JButton [saveFiles.size()];
        for (int i = 0; i < saveFiles.size(); i++){
            final int index = i;
            files[i] = new JButton(saveFiles.get(i));
            files[i].setBounds(x, y, 100, 30);
            files[i].addActionListener(e -> loadGame(index));
            panel.add(files[i]);
            y += 40;
            if (y >= 350){
                x += 110;
                y = 10;
            }
        }
        panel.revalidate();
        panel.repaint();
    }
    private void loadGame(int index){
        //game = new MancalaGame();
        file = "games/" + saveFiles.get(index);
        game = new MancalaGame();
        try {
            game = (MancalaGame) saver.loadObject(file);
            rules = game.getBoard();
            data = rules.getDataStructure();
            if (game.getCurrentPlayer() == game.getPlayers().get(0)){
                currentPlayer = 1;
            } else {
                currentPlayer = 2;
            }
            rules.registerPlayers(game.getPlayers().get(0), game.getPlayers().get(1));
            rules.setPlayer(currentPlayer);
        } catch (Exception e){
            e.printStackTrace();
        }
        displayBoard();
    }
    private void loadSaveFiles(){
        File assetsFolder = new File("assets/games/");
        if (assetsFolder.exists() && assetsFolder.isDirectory()) {
            // List all files in the assets folder
            File[] gamefiles = assetsFolder.listFiles();
    
            // Filter out only the files that match your criteria (e.g., file extension)
            if (gamefiles != null) {
                for (File fname : gamefiles) {
                    //if (isSaveFile(fname)) {
                        saveFiles.add(fname.getName());
                    //}
                }
            }
        } else {
            System.err.println("Assets folder not found!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextUI());
    }
}

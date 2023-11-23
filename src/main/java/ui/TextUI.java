package ui;
import javax.swing.*;

import mancala.AyoRules;
import mancala.GameRules;
import mancala.InvalidMoveException;
import mancala.KalahRules;
import mancala.MancalaDataStructure;
import mancala.MancalaGame;
import mancala.Player;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TextUI extends JFrame {
    private JButton[][] buttons; // Declare the array of buttons
    private JFrame frame;
    private JPanel panel;
    private GameRules rules;
    private MancalaGame game;
    private Player player1;
    private Player player2;
    private int currentPlayer = 1;
    private MancalaDataStructure data;

    public TextUI() {
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
        JButton newGame = new JButton("Start New Game");
        JButton loadGame = new JButton("Load Game");
        newGame.setBounds(225, 150, 150, 30);
        loadGame.setBounds(225, 200, 150, 30);
        panel.add(newGame);
        panel.add(loadGame);
        newGame.addActionListener(e -> startNewGame());
        panel.revalidate();
        panel.repaint();
    }
    private void startNewGame() {
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
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

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
        JLabel playerTurn = new JLabel(game.getCurrentPlayer().getName() + " turn");
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
        save.setBounds(250,250,100,30);
        quit.addActionListener(e -> mainMenuPanel());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextUI());
    }
}

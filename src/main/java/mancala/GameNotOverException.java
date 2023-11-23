package mancala;

public class GameNotOverException extends Exception {
    public GameNotOverException(final String message){
        super(message);
    }
}
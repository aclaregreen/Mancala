package mancala;

public class InvalidMoveException extends Exception {
    public InvalidMoveException(final String message){
        super(message);
    }
}
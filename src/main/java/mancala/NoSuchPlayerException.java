package mancala;

public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException(final String message){
        super(message);
    }
}
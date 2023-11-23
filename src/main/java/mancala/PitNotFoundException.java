package mancala;

public class PitNotFoundException extends Exception {
    public PitNotFoundException(final String message){
        super(message);
    }
}
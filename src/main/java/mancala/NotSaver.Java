package mancala;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class NotSaver{


    public static final String ASSETS_FOLDER = "assets/"

    public void saveObject(Serializable toSave, String filename) throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ASSETS_FOLDER + filename))){
            oos.writeObject(toSave);
        }
    }
    public Serializable loadObject(String filename) throws IOException{
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ASSETS_FOLDER + filename))){
            return (Serializable) ois.readObject();
        }
    }

}
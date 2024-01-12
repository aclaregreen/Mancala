package mancala;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Saver{

    //public static final String ASSETS_FOLDER = "assets/";
    public void saveObject(final Serializable toSave, final String filename) throws IOException{
        //String filePath = ASSETS_FOLDER + filename;
        final String filePath = filename;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(toSave);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public Serializable loadObject(final String filename) throws IOException {
        final String filePath = filename;
        //final String filePath = ASSETS_FOLDER + filename;
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            final Serializable loadedObject = (Serializable) ois.readObject();
            return loadedObject;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
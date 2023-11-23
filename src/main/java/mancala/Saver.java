package mancala;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Saver{

    public static final String ASSETS_FOLDER = "assets/";
    public void saveObject(Serializable toSave, String filename) throws IOException{
        String filePath = ASSETS_FOLDER + filename;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(toSave);
            System.out.println("serialized: " + filePath);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public Serializable loadObject(String filename) throws IOException {
        String filePath = ASSETS_FOLDER + filename;
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Serializable loadedObject = (Serializable) ois.readObject();
            System.out.println("Object loaded successfully.");
            return loadedObject;
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading object:");
            e.printStackTrace();
            return null;
        }
    }
    
}
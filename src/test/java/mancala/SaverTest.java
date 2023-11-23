package mancala;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.Serializable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaverTest {

    private Saver saver;
    private final String testFileName = "testObject.ser";

    @BeforeEach
    public void setUp() {
        saver = new Saver();
    }

    @AfterEach
    public void tearDown() {
        // Clean up: Delete the test file if it exists
        String filePath = Saver.ASSETS_FOLDER + testFileName;
        java.io.File file = new java.io.File(filePath);
        file.delete();
    }

    @Test
    public void testSaveAndLoadObject() {
        // Create a test object to save
        Serializable originalObject = "This is a test object.";

        // Save the test object
        try {
            saver.saveObject(originalObject, testFileName);
        } catch (IOException e) {
            fail("Failed to save object: " + e.getMessage());
        }

        // Load the saved object
        try {
            Serializable loadedObject = saver.loadObject(testFileName);

            // Check if the loaded object is not null
            assertNotNull(loadedObject, "Loaded object should not be null");

            // Check if the loaded object is of the expected type
            assertEquals(originalObject.getClass(), loadedObject.getClass(), "Unexpected class type");

            // Check if the content of the loaded object is as expected
            assertEquals(originalObject, loadedObject, "Object content mismatch");

        } catch (IOException e) {
            fail("Failed to load object: " + e.getMessage());
        }
    }
}

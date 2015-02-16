package utility;

import model.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameObject;

public class LoadSave {

    /*Properties*/

    /*Constructor*/
    
    public LoadSave() {
        //
    }

    /*Methods*/
    
    public Model loadModel(String filename) throws IOException {
        String fileString = getFileString(filename);
        System.out.print(fileString);

        // Model model = (Model)deserialize(fileString, Model.class);
        Model model = Model.fromString(fileString);

        return model;
    }

    public void saveModel(Model model, String filename) {
        String fileContent = model.toString();

        writeToFile(fileContent, filename);
    }
    
    public GameObject loadGameObject(String filename) {
        String fileString = getFileString(filename);
        
        GameObject gameObject = (GameObject)deserialize(fileString, GameObject.class);
        
        return gameObject;
    }
    
    public void saveGameObject(GameObject gameObject, String filename) {
        String fileContent = serialize(gameObject);
        
        writeToFile(fileContent, filename);
    }
    
    // ---------- UTILITY HELPER METHODS ---------
        // ----------                         ---------

    //Returns a (text) file as a single string
    public String getFileString(String filename) {
        try {
            URL resource = ClassLoader.getSystemClassLoader().getResource(filename);
            File file = new File(resource.toURI());
            if (file!=null && file.exists()) {
                //File file = new File(filename);
                StringBuilder fileContents = new StringBuilder((int) file.length());
                Scanner scanner = new Scanner(file);
                String lineSeparator = System.getProperty("line.separator");
                while (scanner.hasNextLine()) {
                    fileContents.append(scanner.nextLine() + lineSeparator);
                }
                scanner.close();
                return fileContents.toString();
            } else {
                return "";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Writes a string to a file (just burps it in)
    public void writeToFile(String fileContent, String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.println(fileContent);
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Convert object to string; ex. serialize(course) may return "{xDisplacement: 1, yDisplacement: 0}" or something like that
    private String serialize(Object object) {
//        Gson gson = new GsonBuilder()
//                .setExclusionStrategies(new ThreadExclStrat())
//                //.serializeNulls() <-- uncomment to serialize NULL fields as well
//                .create();
//        
//        String objectString = gson.toJson(object);
//        
//        return objectString;
        return "";
    }
    
    //Convert string to object; ex. (Course)deserialize(courseString, Course.class) recreates course object ^
    private Object deserialize(String objectString, Class cls) {
//        Gson gson = new GsonBuilder()
//                .setExclusionStrategies(new ThreadExclStrat())
//                //.serializeNulls() <-- uncomment to serialize NULL fields as well
//                .create();
//        
//        Object object = gson.fromJson(objectString, cls);
//        
//        return object;
        return null;
    }
    
    /*Get-Sets*/ 
    
    
    /*Inner class*/

//    private class ThreadExclStrat implements ExclusionStrategy {
//
//        public boolean shouldSkipClass(Class<?> arg0) {
//            return false;
//        }
//
//        public boolean shouldSkipField(FieldAttributes f) {
//            return (f.getDeclaringClass() == Thread.class);
//        }
//
//    }

}

package utility;

import model.Model;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadSave {

    /*Properties*/

    /*Constructor*/
    public LoadSave() {

    }

    /*Methods*/
    public Model loadModel(String filename) {
        String fileString = getFileString(filename);

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ThreadExclStrat())
                //.serializeNulls() <-- uncomment to serialize NULL fields as well
                .create();
        Model model = (Model) gson.fromJson(fileString, Model.class);

        return model;
    }

    public void saveModel(Model model, String filename) {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ThreadExclStrat())
                //.serializeNulls() <-- uncomment to serialize NULL fields as well
                .create();
        String fileContent = gson.toJson(model);

        writeToFile(fileContent, filename);
    }

    public String getFileString(String filename) {
        try {
            File file = new File(filename);
            StringBuilder fileContents = new StringBuilder((int) file.length());
            Scanner scanner = new Scanner(file);
            String lineSeparator = System.getProperty("line.separator");
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            scanner.close();
            return fileContents.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
    
    /*Get-Sets*/     
    
    /*Inner class*/

    private class ThreadExclStrat implements ExclusionStrategy {

        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        public boolean shouldSkipField(FieldAttributes f) {
            return (f.getDeclaringClass() == Thread.class);
        }

    }

}

package me.kawaijoe.rwbinary.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private String filePath;

    public PropertiesReader(String filePath) {
        this.filePath = filePath;
    }

    public String getData(String key) throws KeyInvalidException{
        try {
            File file = new File(filePath);
            FileInputStream fileInput = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(fileInput);
            fileInput.close();
            String text = prop.getProperty(key);

            if(text == null)
                throw new KeyInvalidException("Cannot find paring value from key");

            return(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new KeyInvalidException("An unknown error has occurred");
    }
}

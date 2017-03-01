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

    private String getData(String key) throws KeyInvalidException{
        try {
            File file = new File(filePath);
            FileInputStream fileInput = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(fileInput);
            fileInput.close();

            return(prop.getProperty(key));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new KeyInvalidException("Can't find pairing value from key");
    }
}

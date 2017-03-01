package me.kawaijoe.rwbinary.image;

import javafx.scene.image.Image;
import me.kawaijoe.rwbinary.utility.PropertiesReader;
import me.kawaijoe.rwbinary.utility.Utility;

import java.util.ArrayList;
import java.util.List;

class ImageReader {

    private Image cardBack;
    private ArrayList<Image> cards;
    private PropertiesReader propertiesReaderImage;
    private PropertiesReader propertiesReaderTheme;

    ImageReader(String relativePathToImageProperties) {
        propertiesReaderImage = new PropertiesReader(Utility.relativeToResources(relativePathToImageProperties));
    }

}

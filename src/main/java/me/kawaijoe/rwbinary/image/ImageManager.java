package me.kawaijoe.rwbinary.image;

import javafx.scene.image.Image;
import me.kawaijoe.rwbinary.utility.Utility;

import java.util.List;

public class ImageManager {

    private volatile static ImageManager uniqueInstance;
    private volatile CardPack pack;

    private ImageManager() { }

    public static ImageManager getUniqueInstance() {
        synchronized(ImageManager.class) {
            if (uniqueInstance == null)
                uniqueInstance = new ImageManager();
        }
        return uniqueInstance;
    }

    public void readImages(String pathToImage, String cardBackPath, List<String> filesToIgnore) {
        ImageReader imageReader = new ImageReader(pathToImage, cardBackPath, filesToIgnore);
        pack = imageReader.getPACK();
    }

    public Image getCardBack() {
        return pack.getCardBack();
    }

    public Image getRandomCard() {
        return Utility.getRandomFromList(pack.getCards());
    }

}

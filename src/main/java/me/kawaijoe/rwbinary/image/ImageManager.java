package me.kawaijoe.rwbinary.image;

import javafx.scene.image.Image;
import me.kawaijoe.rwbinary.utility.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageManager {

    private volatile static ImageManager uniqueInstance;
    private volatile Map<String, CardPack> packs = new HashMap<>();

    private ImageManager() { }

    public static ImageManager getUniqueInstance() {
        synchronized(ImageManager.class) {
            if (uniqueInstance == null)
                uniqueInstance = new ImageManager();
        }
        return uniqueInstance;
    }

    public Image getCardBack(String theme) {
        return packs.get(theme).getCardBack();
    }

    public Image getRandomCard(String theme) {
        return Utility.getRandomFromList(packs.get(theme).getCards());
    }

}

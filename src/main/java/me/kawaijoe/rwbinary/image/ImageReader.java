package me.kawaijoe.rwbinary.image;

import javafx.scene.image.Image;
import me.kawaijoe.rwbinary.utility.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class ImageReader {

    private final CardPack PACK;
    private final double MULTIPLIER = 50;

    ImageReader(String pathToImage, String cardBackPath, List<String> filesToIgnore) {

        if(!filesToIgnore.contains(cardBackPath))
            filesToIgnore.add(cardBackPath);

        Image cardBack = new Image(pathToImage + cardBackPath, calWidth(), calHeight(), true,
                true, true);
        List<Image> cards = new ArrayList<>();

        File dir = new File(Utility.relativeToResources(pathToImage));
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if(!filesToIgnore.contains(child.getName())) {
                    cards.add(new Image(pathToImage + child.getName(), calWidth(), calHeight(), true,
                            true, true));
                }
            }
        }

        PACK = new CardPack(cardBack, cards);
    }

    CardPack getPACK() {
        return PACK;
    }

    private double calWidth() {
        return 2.5 * MULTIPLIER;
    }

    private double calHeight() {
        return 3.5 * MULTIPLIER;
    }
}

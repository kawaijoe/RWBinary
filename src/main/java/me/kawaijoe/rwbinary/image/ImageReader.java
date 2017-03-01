package me.kawaijoe.rwbinary.image;

import javafx.scene.image.Image;
import me.kawaijoe.rwbinary.utility.KeyInvalidException;
import me.kawaijoe.rwbinary.utility.PropertiesReader;
import me.kawaijoe.rwbinary.utility.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ImageReader {

    private volatile Map<String, CardPack> packs = new HashMap<>();

    ImageReader(String relativePathToImageProperties) {
        generatePacks(relativePathToImageProperties);
    }

    private void generatePacks(String relativePathToImageProperties) {
        System.out.println("generating packs");
        PropertiesReader propertiesReaderImage = new PropertiesReader(Utility.relativeToResources(relativePathToImageProperties));
        List<String> packDir = getRecursiveStrings(propertiesReaderImage, "cardPack");

        packDir.add("image/config/modern.properties/");
        for(String dir : packDir) {
            packs.put(dir, createCardPack(dir));
        }
    }

    private CardPack createCardPack(String imageConfigDirectory) {
        System.out.println("Creating card pack");
        PropertiesReader propertiesReaderTheme = new PropertiesReader(Utility.relativeToResources(imageConfigDirectory));
        String imageDirectory = null;
        try {
            imageDirectory = propertiesReaderTheme.getData("path");
        } catch (KeyInvalidException e) {
            e.printStackTrace();
        }
        return new CardPack(getCardBack(imageDirectory, propertiesReaderTheme),
                getCards(imageDirectory, propertiesReaderTheme));
    }

    private List<Image> getCards(String imageDirectory, PropertiesReader propertiesReaderTheme) {
        System.out.println("getcards");
        List<Image> list = new ArrayList<>();
        List<String> ignoredFiles = new ArrayList<>();

        try {
            ignoredFiles.add(propertiesReaderTheme.getData("cardBack"));
        } catch (KeyInvalidException e) {
            e.printStackTrace();
        }
        ignoredFiles.addAll(getRecursiveStrings(propertiesReaderTheme, "ignore"));

        File dir = new File(imageDirectory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if(!ignoredFiles.contains(child.getName()))
                    list.add(new Image(imageDirectory + child.getName(), true));
            }
        }

        return list;
    }

    private Image getCardBack(String imageDirectory, PropertiesReader propertiesReaderTheme) {
        System.out.println("getcardback!");
        String name = null;
        try {
            name = propertiesReaderTheme.getData("cardBack");
        } catch (KeyInvalidException e) {
            e.printStackTrace();
        }

        return new Image(imageDirectory + name, true);
    }

    private List<String> getRecursiveStrings(PropertiesReader propertiesReader, String prefix) {
        System.out.println("recursive string start");
        List<String> list = new ArrayList<>();
        int count = 1;

        while(true) {
            System.out.println(prefix + String.valueOf(count));
            try {
                list.add(propertiesReader.getData(prefix + String.valueOf(count)));
            } catch (KeyInvalidException e) {
                break;
            }
            count++;
        }
        System.out.println("recursive string end");
        return list;
    }

    public Map<String, CardPack> getPacks() {
        return packs;
    }
}

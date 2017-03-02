package me.kawaijoe.rwbinary.word;

import me.kawaijoe.rwbinary.utility.Utility;

import java.util.List;
import java.util.Random;

public class WordManager {

    private volatile static WordManager uniqueInstance;
    private volatile List<List<String>> words;

    private WordManager() { }

    public static WordManager getUniqueInstance() {
        synchronized(WordManager.class) {
            if (uniqueInstance == null)
                uniqueInstance = new WordManager();
        }
        return uniqueInstance;
    }

    public void readWords(String path) {
        TextReader textReader = new TextReader(path);
        words = textReader.getWords();
    }

    public String getRandomWord(int length) {
        return Utility.getRandomFromList(words.get(length - 1));
        //int indexLength = length - 1;
        //int randomIndex = new Random().nextInt(words.get(indexLength).size());
        //return words.get(indexLength).get(randomIndex);
        //return "";
    }
}

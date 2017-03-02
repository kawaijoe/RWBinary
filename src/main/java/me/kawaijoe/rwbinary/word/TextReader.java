package me.kawaijoe.rwbinary.word;

import me.kawaijoe.rwbinary.utility.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TextReader {

    private String path;

    // Stores words in an ArrayList of words. Index 0 = 1 char; Index 1 = 2 char.
    private List<List<String>> words = new ArrayList<>();

    TextReader(String relativePath) {
        this.path = Utility.relativeToResources(relativePath);
        readFile();
    }

    private void readFile() {
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            while((line = bufferedReader.readLine()) != null) {
                line = line.replaceAll("\n", "");
                saveToArrayList(line);
            }

            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
            System.out.println(ex);
        } catch(IOException ex) {
            System.out.println("Error reading file '" + path + "'");
        }
    }

    private void saveToArrayList(String word) {
        fixArrayLength(word.length());
        words.get(word.length() - 1).add(word);
    }

    private void fixArrayLength(int length) {
        int arraySize = words.size();
        for(int i = 0; i < length - arraySize; i++) {
            words.add(new ArrayList<>());
        }
    }

    List<List<String>> getWords() {
        return words;
    }

}
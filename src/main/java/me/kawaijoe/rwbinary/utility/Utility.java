package me.kawaijoe.rwbinary.utility;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Utility {

    public static String textToBinary(String text) {
        byte[] bytes = text.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        return binary.toString();
    }

    public static String relativeToResources(String path) {
        return "src/main/resources/" + path;
    }

    public static <T> T getRandomFromList(List<T> list) {
        int randomIndex = new Random().nextInt(list.size());
        return list.get(randomIndex);
    }

}

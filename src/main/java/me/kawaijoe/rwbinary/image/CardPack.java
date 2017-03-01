package me.kawaijoe.rwbinary.image;

import javafx.scene.image.Image;

import java.util.Collections;
import java.util.List;

public class CardPack {

    private final Image cardBack;
    private final List<Image> cards;

    CardPack(Image cardBack, List<Image> cards) {
        this.cardBack = cardBack;
        this.cards = cards;
    }

    public Image getCardBack() {
        return cardBack;
    }

    public List<Image> getCards() {
        return Collections.unmodifiableList(cards);
    }

}

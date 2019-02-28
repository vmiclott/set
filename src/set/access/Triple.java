package set.access;

import java.lang.*;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by victor on 16.05.15.
 */
public class Triple {

    private Card[] cards = new Card[3];

    public Triple(Card card1, Card card2, Card card3) {
        cards[0] = card1;
        cards[1] = card2;
        cards[2] = card3;
    }

    public Triple (Double dbl, Card card3) {
        cards[0] = dbl.getCards()[0];
        cards[1] = dbl.getCards()[1];
        cards[2] = card3;
    }

    public Triple (Set<Card> cards) {
        int i = 0;
        for (Card c : cards) {
            this.cards[i] = c;
            i++;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(cards);
    }

    public Card[] getCards() {
        return cards;
    }

    public boolean isSet() {
        int length = cards[0].getProperties().length;
        int[] prop1 = cards[0].getProperties();
        int[] prop2 = cards[1].getProperties();
        int[] prop3 = cards[2].getProperties();
        for (int i = 0; i < length; i++){
            if ((prop1[i] + prop2[i] + prop3[i]) % 3 != 0) {
                return false;
            }
        }
        return true;
    }

}

package set.access;

/**
 * Created by victor on 16.05.15.
 */
public class Double {

    private Card[] cards = new Card[2];


    public Double(Card card1, Card card2) {
        cards[0] = card1;
        cards[1] = card2;
    }

    public Card[] getCards() {
        return cards;
    }

    public Card getThird() {
        int length = cards[0].getProperties().length;
        int[] properties = new int[length];
        for (int j = 0; j < properties.length; j++) {
            properties[j] = (3 - (cards[0].getProperties()[j] + cards[1].getProperties()[j])%3)%3;
        }
        return new Card(properties);
    }
}

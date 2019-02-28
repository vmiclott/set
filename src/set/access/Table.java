package set.access;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Table {

    private List<Card> totalCards;
    private List<Card> cards;
    private List<Card> tableCards;
    private List<Card> removedCards;
    private Random rng;
    private int numberOfProperties;
    private int[][] cardMatrix;
    private int numberOfCards;


    public List<Card> getTotalCards() {
        return totalCards;
    }

    public List<Card> getRemovedCards() {
        return removedCards;
    }

    public Table(int numberOfProperties) {
        totalCards = new ArrayList<>();
        cards = new ArrayList<>();
        tableCards = new ArrayList<>();
        removedCards = new ArrayList<>();
        this.numberOfProperties = numberOfProperties;
        numberOfCards = (int) Math.pow(3,numberOfProperties);
        cardMatrix = new int[numberOfCards][numberOfProperties];
        nestedForLoop(numberOfCards / 3, 0);
        for (int[] row : cardMatrix) {
            totalCards.add(new Card(row));
            cards.add(new Card(row));
        }
        rng = new Random();
        addCards();

    }

    public void nestedForLoop(int number, int idx) {
        if (idx == cardMatrix[0].length) {
            return;
        }
        int i = 0;
        int k = 0;
        while (i < numberOfCards) {
            for (int j = i; j < i+number; j++) {
                cardMatrix[j][idx] = k%3;
            }
            k++;
            i += number;
        }
        nestedForLoop(number/3, idx+1);
    }
    /*
     * checkt of het geselecteerd triple een set is en of de kaarten op tafel liggen (wsl overbodig)
     * en verwijdert dan de set
     */
    public void removeSet(Triple selectedSet) {
        if(selectedSet.isSet() && containsSet(selectedSet)) {
            for (int i = 0; i < 3 ; i++) {
                removedCards.add(selectedSet.getCards()[i]);
                tableCards.remove(selectedSet.getCards()[i]);
            }
        }
        addCards();
    }

    /*
     * checkt of de geselecteerde set op tafel ligt (wsl overbodig)
     */
    public boolean containsSet(Triple selectedSet) {
        for (int i = 0; i < 3 ; i++) {
            if(!tableCards.contains(selectedSet.getCards()[i])) {
                return false;
            }
        }
        return true;
    }

    /*
     * checkt of er sets op tafel liggen
     */
    public Triple findSet() {
        for (Double dbl : getAllDoubles()) {
            for (Card card : tableCards) {
                if(card.isEqualTo(dbl.getThird())) {
                    return new Triple(dbl.getCards()[0],dbl.getCards()[1],dbl.getThird());
                }
            }
        }
        return null;
    }


    public List<Card> getCards() {
        return cards;
    }

    /*
         * geeft lijst van alle doubles op tafel terug
         */
    public List<Double> getAllDoubles() {
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < tableCards.size() - 1; i++) {

            Card card1 = tableCards.get(i);
            for (int j = i+1; j < tableCards.size(); j++) {
                res.add(new Double(card1, tableCards.get(j)));
            }
        }
        return res;
    }

    public List<Card> getTableCards() {
        return tableCards;
    }


    /*
     * kijkt of er nog kaarten zijn om toe te voegen en of er effectief geen set ligt
     * voegt vervolgens 3 nieuwe random kaarten toe
     * als er geen 12kaarten zijn, gebeurt dit ook
     */
    public void addCards() {
        //TODO: niet juist?
        while ((tableCards.size() < 3*numberOfProperties || (tableCards.size() + removedCards.size() < totalCards.size() && findSet() == null)) && cards.size() > 2) {
            for (int i = 0; i < 3; i++) {
                int index = rng.nextInt(cards.size());
                tableCards.add(cards.get(index));
                cards.remove(index);
            }
        }
    }

}

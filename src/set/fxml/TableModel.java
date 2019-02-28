package set.fxml;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.util.Duration;
import set.access.Card;
import set.access.Table;
import set.access.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: Victor
 */
public class TableModel implements Observable {
    private Timeline timeline;
    private Card[] tableCards = new Card[24];
    private int time = 0;
    private String timeText;
    private Table table;
    private Set<Card> selectedCards;
    private Boolean isSet = false;
    private boolean threeCardsSelected = false;
    private List<InvalidationListener> listenerList = new ArrayList<>();

    public TableModel() {
        table = new Table(Controller.numberOfProperties);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time++;
            StringBuilder builder = new StringBuilder();
            if(time < 600) {
                builder.append(0);
            }
            builder.append(time / 60).append(":");
            if(time%60 < 10) {
                builder.append(0);
            }
            builder.append(time%60);
            timeText = builder.toString();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public String getTimeText() {
        return timeText;
    }
    public int getTime() {
        return time;
    }
    public void increaseTime(int increment) {
        time += increment;
    }
    public boolean ThreeCardsSelected() {
        return threeCardsSelected;
    }
    public Boolean isSet() {
        return isSet;
    }

    public Table getTable(){
        return table;
    }
    public void setTable(Table table) {
        this.table = table;
    }
    public List<Card> getCards(){
        return table.getCards();
    }
    public List<Card> getRemovedCards(){
        return table.getRemovedCards();
    }
    public List<Card> getTableCards(){
        return table.getTableCards();
    }
    public List<Card> getTotalCards(){
        return table.getTotalCards();
    }
    public int getCardsSize(){
        return table.getCards().size();
    }
    public int getTableCardsSize(){
        return table.getTableCards().size();
    }
    public int getRemovedCardsSize(){
        return table.getRemovedCards().size();
    }
    public int getTotalCardsSize(){
        return table.getTotalCards().size();
    }
    public Triple getSet(){
        return table.findSet();
    }
    public void addSelectedCard(Card card){
        selectedCards.add(card);
    }
    public void makeTableCards() {

    }
    public void checkForSet() {
        Triple triple;
        int i = 0;
        if (selectedCards.size()!=3) {
            threeCardsSelected = false;
        } else{
            triple = new Triple(selectedCards);
            if (triple.isSet()) {
                isSet = true;
                table.removeSet(triple);
            } else{
                isSet = false;
            }
        }
        selectedCards.clear();

        if (getCardsSize() == 0 && table.findSet() == null) {
            endgame();
        }
        fireInvalidationEvent();
    }

    public void endgame() {
        isSet = null;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listenerList.remove(listener);
    }

    private void fireInvalidationEvent() {
        for(InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }
}

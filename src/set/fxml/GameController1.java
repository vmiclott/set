package set.fxml;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import set.Main;
import set.access.Card;
import set.access.Table;
import set.access.Triple;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author: Victor
 */
public class GameController1 implements Initializable {

    public TableModel model;
    public GridPane gridPane;
    public Button back;
    public Button refresh;
    public Button showSet;
    public Button isSet;
    private VCard[] cards = new VCard[24];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < gridPane.getChildren().size(); i++){
            cards[i] = ((VCard) gridPane.getChildren().get(i));
        }
        showCards();
    }
    public void showCards() {
        int i = 0;
        for (Card card : model.getTableCards()) {
            cards[i].setCard(card);
            cards[i].setVisible(true);
            i++;
        }
        for (int k = model.getTableCards().size(); k < cards.length; k++) {
            cards[k].setVisible(false);
        }
        int j = 21;
        if (model.getTable().findSet() != null) {
            for (Card card : model.getTable().findSet().getCards()) {
                cards[j].setCard(card);
                j++;
            }
        }
        //numberOfCards.setText(model.getCards().size() + "/" + model.getTotalCards().size());
    }


    public void goBack(ActionEvent actionEvent) {
        /*try {
            seconds[0] = 0;
            timeline.stop();
            Parent root = FXMLLoader.load(Main.class.getResource("fxml/start.fxml"));
            Main.stage.setTitle("Start");
            Main.stage.setScene(new Scene(root, 600, 600));
        } catch (IOException e) {
            System.out.println("scherm ni gevonden");
        }*/
    }

    public void showSet(ActionEvent actionEvent) {
        /*model.increaseTime(10);
        cards[21].setVisible(true);
        cards[22].setVisible(true);
        cards[23].setVisible(true);*/
    }

    public void refresh(ActionEvent actionEvent) {/*
        wrongSet.setText("");
        timeline.stop();
        seconds[0] = 0;
        initialize(null, null);*/
    }



    public void isSet(ActionEvent actionEvent) {
        /*Card[] triple = new Card[3];
        int i = 0;
        if (selectedCards.size()!=3) {
            wrongSet.setText("geen 3kaarten geselecteerd");
        } else{
            for (Card c : selectedCards) {
                triple[i] = c;
                i++;
            }
            Triple t = new Triple(triple[0],triple[1],triple[2]);
            if (t.isSet()) {
                wrongSet.setText("correct! :)");
                table.removeSet(t);
                showCards();
            } else{
                seconds[0] += 10;
                wrongSet.setText("dit is helaas geen set :(");
            }
        }
        for (VBox loc : cardLocs.keySet()) {
            loc.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        selectedCards.clear();

        if (table.getCards().size() == 0 && table.findSet() == null) {
            endgame();
        }*/
    }

    private void endgame() {
        /*timeline.stop();
        wrongSet.setText("Gefeliciteerd uw tijd: " + time.getText());*/
    }

    public void addCard(Event event) {
        /*VBox vBox = (VBox) event.getSource();
        Card card = cardLocs.get(vBox);
        if (selectedCards.contains(card)) {
            selectedCards.remove(card);
            vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        } else{
            selectedCards.add(card);
            vBox.setBackground(new Background(new BackgroundFill(Color.valueOf("#333333"), null, null)));
        }*/

    }

}

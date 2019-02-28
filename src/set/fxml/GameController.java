package set.fxml;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import set.Main;
import set.access.Card;
import set.access.Table;
import set.access.Triple;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author: Victor
 */
public class GameController implements Initializable {


    public Label time;
    public Button back;
    public Button showSet;
    public Button refresh;
    public Button isSet;
    public static Table table;
    public Label wrongSet;
    public Label numberOfCards;
    public GridPane gridPane;
    public static boolean pvm;

    final int[] seconds = {0};
    public Label timeIncrease;
    public Label cardsLeft;
    public Label pscore;
    public Label cscore;
    public Label player;
    public Label computer;
    private int ps;
    private int cs;
    private int sec;
    private Timeline timeline;
    private Timeline timeline1;
    private Map<VBox,Card> cardLocs;
    private Set<Card> selectedCards;
    private VBox[] cards = new VBox[24];
    private ImageView[][] images = new ImageView[24][3];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ps = 0;
        cs = 0;
        pscore.setText(ps + "");
        cscore.setText(cs + "");
        pscore.setVisible(pvm);
        cscore.setVisible(pvm);
        player.setVisible(pvm);
        computer.setVisible(pvm);
        showSet.setVisible(!pvm);
        if(pvm){
            sec = PvmController.sec;
        }
        cardsLeft.setVisible(true);
        numberOfCards.setVisible(true);
        isSet.setDisable(false);
        showSet.setDisable(false);
        for (int i = 0; i < gridPane.getChildren().size(); i++){
            cards[i] = ((VBox) gridPane.getChildren().get(i));
            for (int j = 0; j < 3; j++) {
                images[i][j] = (ImageView) cards[i].getChildren().get(j);
            }
        }
        startTimer();
        timeline1 = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            timeIncrease.setVisible(false);
        }));
        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();
        cardLocs = new HashMap<>();
        selectedCards = new HashSet<>();
        table = new Table(Controller.numberOfProperties);
        showCards();
    }

    public void removeSet(ActionEvent event) {
        table.removeSet(table.findSet());
        showCards();
    }

    public void startTimer() {
        if(timeline != null) {
            timeline.stop();
        }
        if(pvm) {
            seconds[0] = sec;
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(pvm && seconds[0]==-1) {
                //TODO: zorgen dat een set verwijderd wordt
                //System.out.println(table.findSet());
                //table.removeSet(table.findSet());
                //showCards();
                seconds[0] = sec;
                cs++;
                updateLabels();
            }
            if(seconds[0]%60 < 10) {
                time.setText("" + seconds[0]/60 + ":0" + seconds[0]%60);
            } else {
                time.setText("" + seconds[0]/60 + ":" + seconds[0]%60);
            }
            if(pvm) {
                seconds[0]--;
            } else {
                seconds[0]++;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void showFirstCards() {int i = 0;
        for (Card card : table.getTableCards()) {
            setCardOnImage(card, i);
            cards[i].setVisible(true);
            cardLocs.put(cards[i], card);
            i++;
        }
        for (int k = table.getTableCards().size(); k < cards.length-3; k++) {
            cards[k].setVisible(false);
        }
        int j = 21;
        if (table.findSet() != null) {
            for (Card card : table.findSet().getCards()) {
                setCardOnImage(card, j);
                j++;
            }
        }
        numberOfCards.setText(table.getCards().size() + "/" + table.getTotalCards().size());
    }

    private void updateLabels() {
        pscore.setText(ps + "");
        cscore.setText(cs + "");
    }

    public void showCards() {
        int i = 0;
        for (Card card : table.getTableCards()) {
            setCardOnImage(card, i);
            cards[i].setVisible(true);
            cardLocs.put(cards[i], card);
            i++;
        }
        for (int k = table.getTableCards().size(); k < cards.length; k++) {
            cards[k].setVisible(false);
        }
        int j = 21;
        if (table.findSet() != null) {
            for (Card card : table.findSet().getCards()) {
                setCardOnImage(card, j);
                j++;
            }
        }
        numberOfCards.setText(table.getCards().size() + "/" + table.getTotalCards().size());
    }


    public void setCardOnImage(Card card, int vBox) {
        for (int i = 0; i < 3; i++) {
            images[vBox][i].setImage(null);
        }
        String s = "";
        for (int i = 0; i < 3; i++) {
            s += card.getProperties()[i];
        }
        if (card.getProperties()[3] == 0 || card.getProperties()[3] == 2) {
            images[vBox][1].setImage(Main.images.get(s));
        }
        if (card.getProperties()[3] == 1 || card.getProperties()[3] == 2) {
            images[vBox][0].setImage(Main.images.get(s));
            images[vBox][2].setImage(Main.images.get(s));
        }
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            seconds[0] = 0;
            timeline.stop();
            timeline1.stop();
            Parent root = FXMLLoader.load(Main.class.getResource("fxml/start.fxml"),ResourceBundle.getBundle("set/resources/i18n"));
            Main.stage.setTitle(Main.bundle.getString("start"));
            Main.stage.setScene(new Scene(root, 600, 600));
        } catch (IOException e) {
            System.out.println(Main.bundle.getString("screenNotFound"));
        }
    }

    public void showSet(ActionEvent actionEvent) {
        seconds[0] += 20;
        timeIncrease.setText("+20!");
        timeIncrease.setVisible(true);
        cards[21].setVisible(true);
        cards[22].setVisible(true);
        cards[23].setVisible(true);
    }

    public void refresh(ActionEvent actionEvent) {
        wrongSet.setText("");
        timeline.stop();
        timeline1.stop();
        seconds[0] = 0;
        initialize(null, null);
    }


    public void isSet(ActionEvent actionEvent) {
        Card[] triple = new Card[3];
        int i = 0;
        if (selectedCards.size()!=3) {
            wrongSet.setText(Main.bundle.getString("notThreeCards"));
        } else{
            for (Card c : selectedCards) {
                triple[i] = c;
                i++;
            }
            Triple t = new Triple(triple[0],triple[1],triple[2]);
            if (t.isSet()) {
                wrongSet.setText(Main.bundle.getString("correct"));
                table.removeSet(t);
                showCards();
                if(pvm) {
                    ps++;
                    updateLabels();
                    startTimer();
                }
            } else{
                seconds[0] += 10;
                timeIncrease.setText("+10!");
                timeIncrease.setVisible(true);
                wrongSet.setText(Main.bundle.getString("wrong"));
            }
        }
        for (VBox loc : cardLocs.keySet()) {
            loc.setStyle("-fx-border-color:black;"
                    +
                    "-fx-border-width:2;");
        }
        selectedCards.clear();

        if (table.getCards().size() == 0 && table.findSet() == null) {
            endgame();
        }
    }

    private void endgame() {
        showSet.setDisable(true);
        isSet.setDisable(true);
        timeline.stop();
        timeline1.stop();
        cardsLeft.setVisible(false);
        numberOfCards.setVisible(false);
        wrongSet.setText(Main.bundle.getString("endGame") + " " + time.getText());
    }

    public void addCard(Event event) {
        VBox vBox = (VBox) event.getSource();
        Card card = cardLocs.get(vBox);
        if (selectedCards.contains(card)) {
            selectedCards.remove(card);
            vBox.setStyle("-fx-border-color:black;"
                    +
                    "-fx-border-width:2;");
        } else{
            selectedCards.add(card);
            vBox.setStyle("-fx-border-color:red;"
                    +
                    "-fx-border-width:5;");
        }
        if (selectedCards.size()==3) {
            isSet(null);
        }

    }

    public void isSetKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            isSet(null);
        }
    }
}

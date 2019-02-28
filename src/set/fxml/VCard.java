package set.fxml;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import set.Main;
import set.access.Card;

/**
 * @author: Victor
 */
public class VCard extends VBox implements EventHandler<MouseEvent> {
    private ImageView[] images = new ImageView[3];
    private Card card = null;
    private TableModel model;

    public VCard () {
        setOnMouseClicked(this);
    }


    public TableModel getModel() {
        return model;
    }

    public void setModel(TableModel model) {
        this.model = model;
    }

    public Card getCard() {
        return card;
    }


    public void setCard(Card card) {
        this.card = card;
        if (card != null) {
            String s = "";
            for (int i = 0; i < 3; i++) {
                s += card.getProperties()[i];
            }
            if (card.getProperties()[3] == 0 || card.getProperties()[3] == 2) {
                images[1].setImage(Main.images.get(s));
            }
            if (card.getProperties()[3] == 1 || card.getProperties()[3] == 2) {
                images[0].setImage(Main.images.get(s));
                images[2].setImage(Main.images.get(s));
            }
        }
    }


    @Override
    public void handle(MouseEvent event) {
        model.addSelectedCard(card);
    }
}

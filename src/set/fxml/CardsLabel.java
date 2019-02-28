package set.fxml;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;

/**
 * @author: Victor
 */
public class CardsLabel extends Label implements InvalidationListener {
    private TableModel model;

    public CardsLabel() {
    }

    public TableModel getModel() {
        return model;
    }

    public void setModel(TableModel model) {
        this.model = model;
        model.addListener(this);
    }

    @Override
    public void invalidated(Observable observable) {
        setText(model.getCards().size() + "/" + model.getTotalCards().size());
    }
}
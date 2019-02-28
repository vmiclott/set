package set.fxml;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;

/**
 * @author: Victor
 */
public class TimeLabel extends Label implements InvalidationListener {
    private TableModel model;

    public TimeLabel() {
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
        setText(model.getTimeText());
    }
}
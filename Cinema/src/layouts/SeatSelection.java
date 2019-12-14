package layouts;

import cinema.Cinema;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import resources.tools.Tools;

public class SeatSelection {
    BorderPane seatSelectionView;
    Cinema cinema;
    Label label;

    public void setLayout() {
        label = new Label("Pick a seat");

        label.setId("layoutTitle");

        seatSelectionView.setTop(label);
        seatSelectionView.setCenter(Tools.getUnderConstructionLabel());
    }

    public BorderPane getLayout() {
        return seatSelectionView;
    }

    public SeatSelection(Cinema cinema) {
        seatSelectionView = new BorderPane();
        this.cinema = cinema;

        setLayout();
    }
}

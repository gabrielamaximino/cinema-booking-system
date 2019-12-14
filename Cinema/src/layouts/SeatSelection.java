package layouts;

import cinema.Cinema;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import models.Movie;
import resources.tools.Tools;

public class SeatSelection {
    BorderPane seatSelectionView;
    Cinema cinema;
    Movie movie;
    Label label;

    public void setLayout() {
        label = new Label(movie.title + " – seat selection");

        label.setId("layoutTitle");

        seatSelectionView.setTop(label);
        seatSelectionView.setCenter(Tools.getUnderConstructionLabel());
    }

    public BorderPane getLayout() {
        return seatSelectionView;
    }

    public SeatSelection(Movie movie, Cinema cinema) {
        seatSelectionView = new BorderPane();
        this.movie = movie;
        this.cinema = cinema;

        setLayout();
    }
}

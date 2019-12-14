package layouts;

import cinema.Cinema;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import models.Movie;
import resources.tools.Tools;

public class SeatSelection {
    BorderPane seatSelectionView;
    Cinema cinema;
    Movie movie;
    Label layoutTitle;
    Button backButton;

    public void setLayout() {
        layoutTitle = new Label(movie.title + " – seat selection");

        layoutTitle.setId("layoutTitle");

        seatSelectionView.setTop(layoutTitle);
        seatSelectionView.setCenter(Tools.getUnderConstructionLabel());
        seatSelectionView.setBottom(backButton);
    }

    public BorderPane getLayout() {
        return seatSelectionView;
    }

    public SeatSelection(Movie movie, Cinema cinema) {
        seatSelectionView = new BorderPane();
        backButton = new Button("Back to Movies");
        this.movie = movie;
        this.cinema = cinema;

        backButton.setOnMouseClicked(e -> cinema.setRootCenterLayout(cinema.movies.getLayout()));

        setLayout();
    }
}

package layouts;

import cinema.Cinema;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
        seatSelectionView.setCenter(seatsLayout());
        seatSelectionView.setBottom(backButton);
    }

    public BorderPane getLayout() {
        return seatSelectionView;
    }

    private GridPane seatsLayout(){

        GridPane seats = new GridPane();
        seats.setVgap(5);
        seats.setHgap(5);
        seats.setMaxSize(700, 100);

        int i = 0, j = 0;

        for (boolean seat : this.movie.sala.getSeats()){

            Button seatButton = new Button();
            seatButton.setId(seat ? "taken-seat" : "free-seat");

            seats.add(seatButton, i, j);

            i++;

            if(i == 13){
                i = 0;
                j++;
            }

            if(i == 8){
                Pane empty = new Pane();
                empty.setMinWidth(30);
                seats.add(empty, i, j);
                i++;
            }



        }

        return seats;

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

package layouts;

import cinema.Cinema;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import models.Acquisition;
import models.Movie;
import models.Seat;

public class SeatSelection {

    private BorderPane seatSelectionView;
    private FlowPane bottomButtons;
    private Cinema cinema;
    private Movie movie;
    private Label layoutTitle;
    private Button backButton, addButton, addHalvesButton;
    private Acquisition acquisition;

    public void setLayout() {
        layoutTitle = new Label(movie.title + " – seat selection");

        layoutTitle.setId("layoutTitle");

        seatSelectionView.setTop(layoutTitle);
        seatSelectionView.setCenter(seatsLayout());
        seatSelectionView.setBottom(bottomButtons);
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

        char[] columns = new char[]{'A','B','C','D','E','F','G'};

        for (boolean seat : this.movie.sala.getSeats()){

            Seat seatButton = new Seat(columns[j], i);
            seatButton.setId(seat ? "taken-seat" : "free-seat");

            seatButton.setOnMouseClicked( e -> {

                if(seatButton.getId().equals("free-seat"))

                    seatButton.setId("chosen-seat");
                    acquisition.seats.add(seatButton);

            });

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

    public void finishLayout(){

        layoutTitle.setText(movie.title + " – acquisition finished");

        seatSelectionView.setCenter(new Label("Good Movie!"));

        bottomButtons.getChildren().removeAll(addButton, addHalvesButton);

        cinema.cart.setLayout();

    }

    public SeatSelection(Movie movie, Cinema cinema) {

        seatSelectionView = new BorderPane();
        bottomButtons = new FlowPane();
        bottomButtons.setAlignment(Pos.CENTER);

        this.movie = movie;
        this.cinema = cinema;

        acquisition = new Acquisition(movie);

        backButton = new Button("Back to Movies");
        backButton.setOnMouseClicked(e -> cinema.setRootCenterLayout(cinema.movies.getLayout()));

        addButton = new Button("Add To Cart");
        addButton.setOnMouseClicked( e -> {
            this.cinema.cart.addAcquisition(acquisition);
            finishLayout();
        });

        addHalvesButton = new Button("Add to Cart as Halves");
        addHalvesButton.setOnMouseClicked( e -> {
            acquisition.half = true;
            this.cinema.cart.addAcquisition(acquisition);
            finishLayout();
        });

        bottomButtons.getChildren().addAll(backButton, addButton, addHalvesButton);

        setLayout();
    }
}

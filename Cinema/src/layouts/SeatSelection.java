package layouts;

import cinema.Cinema;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import models.Movie;
import models.Seat;

public class SeatSelection {
    BorderPane seatSelectionView;
    GridPane bottomButtons;
    Cinema cinema;
    Movie movie;
    Label layoutTitle;
    Button backButton, nextButton;
    int seatLine, seatColumn;

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

        for (boolean seat : this.movie.room.getSeats()){

            Button seatButton = new Button();
            seatButton.setId(seat ? "taken-seat" : "free-seat");

            int finalI = i, finalJ = j;

            seatButton.setOnMouseClicked(e -> addToCart(finalI, finalJ));

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
        bottomButtons = new GridPane();
        backButton = new Button("Back to Movies");
        nextButton = new Button("Go to Cart");
        this.movie = movie;
        this.cinema = cinema;

        backButton.setOnMouseClicked(e -> cinema.setRootCenterLayout(cinema.movies.getLayout()));
        nextButton.setOnMouseClicked(e -> goToCart());

        bottomButtons.setAlignment(Pos.BOTTOM_CENTER);
        bottomButtons.minWidth(Region.USE_COMPUTED_SIZE);
        bottomButtons.add(backButton, 0, 0);
        bottomButtons.add(nextButton, 1, 0);

        setLayout();
    }

    private void addToCart(int i, int j) {
        seatLine = i;
        seatColumn = j;
    }

    private void goToCart() {
        cinema.cart.seats.add(new Seat(seatLine, seatColumn, movie, cinema));
        cinema.setRootCenterLayout(cinema.cart.getLayout());
    }
}

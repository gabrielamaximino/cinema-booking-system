package layouts;

import cinema.Cinema;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import models.Acquisition;
import models.Movie;
import models.Seat;

import java.io.IOException;

public class SeatSelection {

    private BorderPane seatSelectionView;
    private FlowPane bottomButtons;
    private Cinema cinema;
    private Movie movie;
    private Label layoutTitle;
    private Button backButton, addTicketButton, addStudentTicketButton, cartButton;
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

            //contornando o problema do gap
            int seatI = i > 8 ? i-1 : i;

            Seat seatButton = new Seat(columns[j], seatI, 12 * j + seatI);
            seatButton.setMinWidth(50);
            seatButton.setMinHeight(50);
            
            seatButton.setId(seat ? "taken-seat" : "free-seat");

            seatButton.setOnMouseClicked( e -> {

                if(seatButton.getId().equals("free-seat")){
                    seatButton.setId("chosen-seat");
                    acquisition.seats.add(seatButton);
                }

                else if(seatButton.getId().equals("chosen-seat")){
                    seatButton.setId("free-seat");
                    acquisition.seats.remove(seatButton);
                }



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

        cinema.cart.updateCartButton();

        layoutTitle.setText(movie.title + " – selection finished");

        seatSelectionView.setCenter(new Label("You can go to Cart now. Good Movie! "));

        bottomButtons.getChildren().removeAll(addTicketButton, addStudentTicketButton);

        bottomButtons.getChildren().add(cartButton);

        cinema.cart.setLayout();

    }

    public SeatSelection(Movie movie, Cinema cinema) {

        seatSelectionView = new BorderPane();
        bottomButtons = new FlowPane();
        bottomButtons.setAlignment(Pos.CENTER);

        this.movie = movie;
        this.cinema = cinema;

        acquisition = new Acquisition(movie, cinema);

        backButton = new Button("Back to Movies");
        backButton.setOnMouseClicked(e -> cinema.setRootCenterLayout(cinema.movies.getLayout()));

        addTicketButton = new Button("Add ticket to Cart");
        addTicketButton.setOnMouseClicked(e -> {
            if(acquisition.seats.size() > 0) {
                this.cinema.cart.addAcquisition(acquisition);
                saveAcquisition(acquisition);
                finishLayout();
            }
        });

        addStudentTicketButton = new Button("Add student ticket to cart");
        addStudentTicketButton.setOnMouseClicked(e -> {
            if(acquisition.seats.size() > 0) {
                acquisition.half = true;
                this.cinema.cart.addAcquisition(acquisition);
                saveAcquisition(acquisition);
                finishLayout();
            }
        });

        cartButton = new Button("Go to Cart");

        cartButton.setOnMouseClicked(e -> cinema.setRootCenterLayout(cinema.cart.getLayout()));

        bottomButtons.getChildren().addAll(backButton, addTicketButton, addStudentTicketButton);

        setLayout();
    }

    private void saveAcquisition(Acquisition acquisition){

        for(Seat seat : acquisition.seats){
            try {
                movie.sala.reservaAssento(seat.getNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

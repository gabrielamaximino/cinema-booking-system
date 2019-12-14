package models;

import cinema.Cinema;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import layouts.SeatSelection;

public class Movie{
    public String title;
    public int length;
    public Image poster;
    Cinema cinema;

    public Movie(String title, int length, Image poster, Cinema cinema) {
        this.title = title;
        this.length = length;
        this.poster = poster;
        this.cinema = cinema;
    }

    public ImageView getPosterImageView() {
        ImageView img = new ImageView(poster);
        img.setOnMouseClicked(e -> setSelectSeatView());
        return img;
    }

    private void setSelectSeatView() {
        BorderPane seatView = new BorderPane();
        Label title = new Label("Seat Selection");

        title.setId("layoutTitle");
        seatView.setTop(title);
        seatView.setCenter(SeatSelection.getLayout());

        cinema.setRootCenterLayout(seatView);
    }
}

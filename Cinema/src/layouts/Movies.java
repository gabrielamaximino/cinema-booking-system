package layouts;

import cinema.Cinema;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import models.Movie;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class Movies {
    private FlowPane flowPane = new FlowPane();
    private BorderPane moviesView = new BorderPane();
    private Label title = new Label("Movies");
    ArrayList<Movie> movies;
    Cinema cinema;
    
    public void setFlowPane() {
        flowPane.getChildren().clear();
        flowPane.getStyleClass().addAll("flowpane");

        for (Movie movie : movies) flowPane.getChildren().add(movie.getPosterImageView());
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public void setLayout() {
        title.setId("layoutTitle");
        moviesView.setTop(title);
        moviesView.setCenter(getFlowPane());
    }

    public BorderPane getLayout() {
        return moviesView;
    }

    public Movies(Cinema cinema) {
        this.cinema = cinema;
        Cart cart = new Cart(cinema);

        movies = new ArrayList<Movie>();

        movies.add(new Movie("Avengers: End-Game", movies.size(), 120, new Image("/resources/images/vingadores.jpg"),
                cinema));
        movies.add(new Movie("Star-Wars", movies.size(), 180, new Image("/resources/images/star-wars.jpg"),
                cinema));
        movies.add(new Movie("A Star is Born", movies.size(), 80, new Image("/resources/images/star-is-born.jpg"),
                cinema));

        setFlowPane();
        setLayout();
    }
}
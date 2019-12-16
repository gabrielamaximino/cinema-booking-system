package cinema;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import layouts.Cart;
import layouts.Home;
import layouts.Movies;
import com.sun.javafx.css.StyleManager;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Acquisition;


public class Cinema extends Application {
    
    public Button homeButton, moviesButton, cartButton;
    private BorderPane root;
    Scene main;

    public Home home;
    public Cart cart;
    public Movies movies;

    double xOffset, yOffset;


    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        primaryStage.initStyle(StageStyle.UNDECORATED);

        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet("/resources/css/style.css");

        root = new BorderPane();

        Label navigator = new Label("MENU");
        navigator.setId("navigator");

        navigator.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        navigator.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        root.setTop(navigator);

        home = new Home(this);
        movies = new Movies(this);
        cart = new Cart(this);

        setHomeButton();
        setMoviesButton();
        setCartButton();

        setInitialHomeView();

        Pane empty = new Pane();
        empty.setMinHeight(240);

        Button exit = new Button("Exit");
        exit.setOnMouseClicked(e -> {
            cart.undoAcquisitions();
            Platform.exit();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(homeButton, moviesButton, cartButton, empty, exit);
        vbox.getStyleClass().addAll("vbox");
        root.setLeft(vbox);

        root.setCenter(home.getLayout());
        
        main = new Scene(root, 1000, 600);

        primaryStage.setScene(main);
        primaryStage.setTitle("Movie Theater Booking System");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void setHomeButton() {
        homeButton = new Button("Home");

        homeButton.setId("homeButton");
        homeButton.setOnAction(e -> root.setCenter(home.getLayout()));
    }

    public void setMoviesButton() {
        moviesButton = new Button("Movies");

        moviesButton.setId("moviesButton");
        moviesButton.setOnAction(e -> root.setCenter(movies.getLayout()));
    }

    public void setCartButton() {
        cartButton = new Button("Cart (" + cart.getCartSize() + ")");

        cartButton.setId("cartButton");
        cartButton.setOnAction(e -> root.setCenter(cart.getLayout()));
    }

    private void setInitialHomeView() {
        root.setCenter(home.getLayout("Welcome!"));
    }

    private void setCartView() {
        BorderPane cartView = new BorderPane();
        Label title = new Label("Your cart so far");

        title.setId("layoutTitle");
        cartView.setTop(title);
        cartView.setCenter(cart.getLayout());

        root.setCenter(cartView);
    }

    public void setRootCenterLayout(Node layout) {
        root.setCenter(layout);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

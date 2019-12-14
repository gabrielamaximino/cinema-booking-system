/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import javafx.scene.Node;
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


/**
 *
 * @author gabri
 */
public class Cinema extends Application {
    
    Button m1, m2, m3;
    public BorderPane root;
    Stage window;
    Scene main;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        window = primaryStage;
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet("/resources/css/style.css");

        root = new BorderPane();
       
        Label navigator = new Label("NAVIGATOR");
        navigator.setId("navigator");
        root.setTop(navigator);
        
        m1 = new Button("Home");
        m2 = new Button("Movies");
        m3 = new Button("Cart");
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(m1, m2, m3); 
        vbox.getStyleClass().addAll("vbox");
        root.setLeft(vbox);
        
        
        Home home = new Home();
        root.setCenter(home.getLayout());
        
        m1.setId("homeButton");
        m1.setOnAction(e -> root.setCenter(home.getLayout()));
        
        Movies movies = new Movies(this);
        m2.setId("moviesButton");
        m2.setOnAction(e -> root.setCenter(movies.getLayout()));

        Cart cart = new Cart();
        m3.setId("cartButton");
        m3.setOnAction(e -> root.setCenter(cart.getLayout()));

        main = new Scene(root, 1000, 600);
        
        window.setScene(main);
        window.setTitle("Movie Theater Booking System");
        window.setResizable(false);
        window.show();
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

package layouts;

import cinema.Cinema;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import resources.tools.Tools;

public class Home {
    private BorderPane homeView;
    private Label label;
    private Cinema cinema;
    private boolean isInitial;
    private Pagination homePages;
    
    private Pane createPage(int indexPage) {
        Pane pageBox = new Pane();
        pageBox.setId("pageBox" + indexPage);
        
        if (indexPage != 1) {
            Button buynowButton = new Button("BUY NOW!");
            buynowButton.setId("buynowButton");
            buynowButton.relocate(300, 425);
            buynowButton.setOnMouseClicked (e -> cinema.setRootCenterLayout(cinema.movies.getLayout()));
            pageBox.getChildren().addAll(buynowButton);
        }
        
        return pageBox;
    }
    
    public void setLayout() {
        label = new Label("Home");

        label.setId("layoutTitle");

        homeView.setTop(label);
        
        homePages = new Pagination(3);
        
        homePages.setPageFactory((n -> createPage(n)));
        homeView.setCenter(homePages);
        
         Timeline fiveSecondsTimer = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            int pos = (homePages.getCurrentPageIndex()+1) % homePages.getPageCount();
            homePages.setCurrentPageIndex(pos);
        }));
         
        fiveSecondsTimer.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsTimer.play();
    }

    public void setLayout(String homeText) {
        label = new Label(homeText);

        label.setId("layoutTitle");

        homeView.setTop(label);
        homeView.setCenter(Tools.getUnderConstructionLabel());
    }

    public BorderPane getLayout() {
        if (isInitial) {
            setLayout();
            isInitial = false;
        }

        return homeView;
    }

    public BorderPane getLayout(String homeText) {
        setLayout(homeText);

        return homeView;
    }

    public Home(Cinema cinema) {
        homeView = new BorderPane();
        this.cinema = cinema;
        isInitial = true;

        setLayout();
    }
}

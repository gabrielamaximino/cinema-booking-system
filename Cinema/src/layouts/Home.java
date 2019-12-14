package layouts;

import cinema.Cinema;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import resources.tools.Tools;

public class Home {
    private BorderPane homeView;
    private Label label;
    private Cinema cinema;
    private boolean isInitial;

    public void setLayout() {
        label = new Label("Home");

        label.setId("layoutTitle");

        homeView.setTop(label);
        homeView.setCenter(Tools.getUnderConstructionLabel());
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

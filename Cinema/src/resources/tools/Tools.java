package resources.tools;

import javafx.scene.control.Label;

public class Tools {
    public static Label getUnderConstructionLabel() {
        Label label = new Label("This page is currently under construction.");
        label.setId("underConstruction");

        return label;
    }
}

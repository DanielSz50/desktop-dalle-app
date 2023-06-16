package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuView implements IMenuView {
    private final Button button1 = new Button("Browse Images");
    private final Button button2 = new Button("Generate Image");
    private final Scene scene;
    private final Stage stage;

    public MenuView(Stage stage) {
        VBox menuBox = new VBox(10);
        menuBox.setPadding(new Insets(10));
        menuBox.setAlignment(Pos.CENTER);
        menuBox.getChildren().addAll(button1, button2);

        this.scene = new Scene(menuBox, 200, 150);
        this.stage = stage;
        registerButtons(stage);
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void setViewScene() {
        stage.setScene(scene);
    }

    private void registerButtons(Stage stage) {
        ImageBrowserView browserView = new ImageBrowserView(stage, this);
        GenerateImageView generateView = new GenerateImageView(stage, this);

        button1.setOnAction(event -> {
            browserView.setViewScene();
        });

        button2.setOnAction(event -> {
            generateView.setViewScene();
        });
    }

}

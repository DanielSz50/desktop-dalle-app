package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mediator.GenerateImageMediator;

import java.io.File;

public class GenerateImageView implements IGenerateImageView{
    private final ImageView imageView;
    private final Scene scene;
    private Button backToMenuButton;
    private Button generateButton;
    private final TextField generateQueryTextField;
    private final Stage stage;
    private final MenuView menuView;

    private final GenerateImageMediator mediator = new GenerateImageMediator(this);

    public GenerateImageView(Stage stage, MenuView menuView) {
        this.menuView = menuView;
        this.stage = stage;

        imageView = new ImageView();
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);

        registerButtons();

        generateQueryTextField = new TextField();
        generateQueryTextField.setPrefWidth(400);

        HBox hbox = new HBox(10, generateQueryTextField, generateButton, backToMenuButton);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, imageView, hbox);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        this.scene = new Scene(vbox, 1024, 1024);
    }

    @Override
    public void setViewScene() {
        stage.setScene(this.scene);
    }

    @Override
    public void setImage(String filepath) {
        showImage(filepath);
    }

    private void showImage(String filepath) {
        File file = new File(filepath);
        javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString());
        imageView.setImage(image);
    }

    private void registerButtons() {
        this.backToMenuButton = new Button("Back to Menu");
        this.backToMenuButton.setOnAction(event -> {
            this.menuView.setViewScene();
        });

        this.generateButton = new Button("Generate");
        this.generateButton.setOnAction(event -> {
            mediator.generateImage("", generateQueryTextField.getText());
        });
    }
}

package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mediator.ImageBrowserMediator;

import java.io.File;
import java.util.List;

public class ImageBrowserView implements IImageBrowserView {
    private List<model.Image> imageList;
    private final ImageView imageView;
    private int currentIndex = 0;
    private final Scene scene;
    private Button backToMenuButton;
    private Button previousButton;
    private Button nextButton;
    private final Stage stage;
    private final MenuView menuView;
    private final ImageBrowserMediator imageMediator = new ImageBrowserMediator(this);;

    public ImageBrowserView(Stage stage, MenuView menuView) {
        this.menuView = menuView;
        this.stage = stage;

        imageView = new ImageView();
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);

        registerButtons();

        HBox hbox = new HBox(10, previousButton, nextButton, backToMenuButton);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, imageView, hbox);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        this.scene = new Scene(vbox, 1024, 1024);
    }

    @Override
    public void setViewScene() {
        imageMediator.loadImageList();
        stage.setScene(this.scene);
    }

    @Override
    public void setImageList(List<model.Image> imageList) {
        this.imageList = imageList;
        showImage(currentIndex);
    }

    private void showPreviousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            showImage(currentIndex);
        }
    }

    private void showNextImage() {
        if (currentIndex < imageList.size() - 1) {
            currentIndex++;
            showImage(currentIndex);
        }
    }

    private void showImage(int index) {
        if (imageList == null || imageList.size() == 0) return;

        String imagePath = imageList.get(index).getFilepath();
        File file = new File(imagePath);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    private void registerButtons() {
        this.previousButton = new Button("Previous");
        this.previousButton.setOnAction(event -> {
            showPreviousImage();
        });

        this.nextButton = new Button("Next");
        this.nextButton.setOnAction(event -> {
            showNextImage();
        });

        this.backToMenuButton = new Button("Back to Menu");
        this.backToMenuButton.setOnAction(event -> {
            this.menuView.setViewScene();
        });
    }
}

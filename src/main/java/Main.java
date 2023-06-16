import javafx.application.Application;
import javafx.stage.Stage;
import view.MenuView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MenuView menuView = new MenuView(primaryStage);

        primaryStage.setTitle("Image Generator");
        primaryStage.setScene(menuView.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
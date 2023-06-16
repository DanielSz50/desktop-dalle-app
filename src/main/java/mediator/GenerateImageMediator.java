package mediator;

import command.GenerateImageCommand;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import view.IGenerateImageView;

public class GenerateImageMediator implements IGenerateImageMediator {
    private final GenerateImageCommand generateImageCommand = new GenerateImageCommand();
    private final IGenerateImageView imageView;

    public GenerateImageMediator(IGenerateImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void generateImage(String title, String query) {
        generateImageCommand.reset();
        generateImageCommand.setTitle(title);
        generateImageCommand.setQuery(query);
        generateImageCommand.setOnSucceeded(new GenerateImageSuccessHandler());
        generateImageCommand.start();
    }

    private class GenerateImageSuccessHandler implements EventHandler<WorkerStateEvent> {

        @Override
        public void handle(WorkerStateEvent event) {
            imageView.setImage((String) event.getSource().getValue());
        }
    }
}

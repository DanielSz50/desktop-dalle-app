package mediator;

import command.LoadImageListCommand;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import model.Image;
import view.IImageBrowserView;

import java.util.List;

public class ImageBrowserMediator implements IImageBrowserMediator {
    private final LoadImageListCommand loadImageListCommand = new LoadImageListCommand();
    private final IImageBrowserView browserView;


    public ImageBrowserMediator(IImageBrowserView browserView) {
        this.browserView = browserView;
    }

    @Override
    public void loadImageList() {
        loadImageListCommand.reset();
        loadImageListCommand.setOnSucceeded(new LoadImageListSuccessHandler());
        loadImageListCommand.start();
    }

    private class LoadImageListSuccessHandler implements EventHandler<WorkerStateEvent> {

        @Override
        public void handle(WorkerStateEvent event) {
            browserView.setImageList((List<Image>) event.getSource().getValue());
        }
    }
}

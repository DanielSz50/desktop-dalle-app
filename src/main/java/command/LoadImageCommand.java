package command;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.Image;
import storage.PgImageStore;
import storage.Postgres;

public class LoadImageCommand extends Service<Image> {
    private int imageID;
    private final PgImageStore imageStore = new PgImageStore(new Postgres());

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    @Override
    protected Task<Image> createTask() {
        return new Task<>() {
            @Override
            protected Image call() {
                return imageStore.retrieveByID(imageID);
            }
        };
    }
}

package command;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.Image;
import storage.PgImageStore;
import storage.Postgres;

import java.util.List;

public class LoadImageListCommand extends Service<List<Image>> {
    private final PgImageStore imageStore = new PgImageStore(new Postgres());

    @Override
    protected Task<List<Image>> createTask() {
        return new Task<>() {
            @Override
            protected List<Image> call() {
                return imageStore.retrieveAll();
            }
        };
    }
}

package command;

import generator.Dalle;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import storage.PgImageStore;
import storage.Postgres;


public class GenerateImageCommand extends Service<String> {
    private String title;
    private String query;

    private final PgImageStore imageStore = new PgImageStore(new Postgres());
    private final Dalle dalle = new Dalle();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    protected Task<String> createTask() {
        return new Task<>() {
            @Override
            protected String call() {
                String filepath = dalle.generateImage(query);
                imageStore.insert(filepath, title, query);

                return filepath;
            }
        };
    }
}

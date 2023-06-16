package storage;

import model.Image;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class PgImageStore implements ImageStore {
    private final QueryRunner runner;

    public PgImageStore(Postgres pg) {
        this.runner = new QueryRunner(pg);
    }

    @Override
    public void insert(String filepath, String title, String query) {
        String statement = "INSERT INTO images (filepath, title, query, created, updated) " +
                "VALUES (?, ?, ?, current_timestamp, current_timestamp)";

        try {
            runner.update(statement,
                    filepath,
                    title,
                    query
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image retrieveByID(int id) {
        String statement = "SELECT * FROM images WHERE id = ? LIMIT 1";
        ScalarHandler<Image> handler = new ScalarHandler<>();

        Image image;
        try {
            image = runner.query(statement, handler, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return image;
    }

    @Override
    public List<Image> retrieveAll() {
        String statement = "SELECT * FROM images";
        BeanListHandler<Image> handler = new BeanListHandler<>(Image.class);

        List<Image> imageList = List.of();
        try {
            imageList = runner.query(statement, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageList;
    }
}

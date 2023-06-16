package storage;

import model.Image;

import java.util.List;

public interface ImageStore {
    void insert(String filepath, String title, String query);

    Image retrieveByID(int id);

    List<Image> retrieveAll();
}

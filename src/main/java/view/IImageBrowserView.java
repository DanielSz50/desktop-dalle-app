package view;

import model.Image;

import java.util.List;

public interface IImageBrowserView {
    void setViewScene();
    void setImageList(List<Image> imageList);
}

package danekerscode.socialmediaapi.service.i;


import danekerscode.socialmediaapi.model.Image;
import danekerscode.socialmediaapi.model.constants.ImageAddress;

public interface ImageService extends ParentService<Image>{
    void attachImage(Image save, ImageAddress valueOf, Integer id);
}

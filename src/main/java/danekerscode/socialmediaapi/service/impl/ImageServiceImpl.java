package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.model.Image;
import danekerscode.socialmediaapi.model.constants.ImageAddress;
import danekerscode.socialmediaapi.resository.ImageRepository;
import danekerscode.socialmediaapi.service.i.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static danekerscode.socialmediaapi.utils.Converter.toImage;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Image save(Object t) {
        return imageRepository.save(toImage((MultipartFile) t));
    }

    public void attachImage(Image image, ImageAddress address, Integer id) {
        switch (address) {
            case User_AVATAR -> jdbcTemplate.update("update users set image_id = ?  where id = ?", image.getId(), id);
            case Channel_AVATAR -> jdbcTemplate.update("update channel set image_id = ? where id = ?" , image.getId() , id);
        }
    }

    @Override
    public void deleteByID(Integer id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Optional<Image> getById(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }
}

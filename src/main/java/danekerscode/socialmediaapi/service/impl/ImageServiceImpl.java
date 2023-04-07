package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.model.Image;
import danekerscode.socialmediaapi.model.utils.ImageAddress;
import danekerscode.socialmediaapi.resository.ImageRepository;
import danekerscode.socialmediaapi.service.i.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Image save(Object t) {
        MultipartFile file = (MultipartFile) t;
        Image image;
        try {
            image = Image.builder()
                    .originalName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .bytes(file.getBytes())
                    .size(file.getSize())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imageRepository.save(image);
    }
    public void attachImage(Image image , ImageAddress address , Integer id){
        switch (address){
            case User_AVATAR -> jdbcTemplate.update("update users set image_id = ?  where id = ?" , image.getId() ,id);
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

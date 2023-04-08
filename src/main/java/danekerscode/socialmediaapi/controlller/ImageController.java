package danekerscode.socialmediaapi.controlller;

import danekerscode.socialmediaapi.model.Image;
import danekerscode.socialmediaapi.model.constants.ImageAddress;
import danekerscode.socialmediaapi.service.i.ImageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/{address}/{id}")
    public ResponseEntity<?> save(@PathVariable String address,
                                  @PathVariable Integer id ,
                                  @RequestBody @NonNull MultipartFile file){
        imageService.attachImage(imageService.save(file) , ImageAddress.valueOf(address),id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImagesById(@PathVariable("id") Integer id){
        Image image = imageService.getById(id).orElseThrow();
        return ResponseEntity.
                ok().
                header("file" , image.getOriginalName()).
                contentType(MediaType.valueOf(image.getContentType())).
                contentLength(image.getSize()).
                body(
                        new InputStreamResource(
                                new ByteArrayInputStream(
                                        image.getBytes())
                        )
                );
    }

    @DeleteMapping("/{id}") // TODO
    public ResponseEntity<?> delete(@PathVariable Integer id){
        imageService.deleteByID(id);
        return ResponseEntity.ok("image with " + id + " deleted");
    }
}

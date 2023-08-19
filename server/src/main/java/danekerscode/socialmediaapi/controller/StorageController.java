package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.constants.FileAddress;
import danekerscode.socialmediaapi.service.StorageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping(consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> save(
            @RequestParam FileAddress address,
            @RequestParam Integer id,
            @RequestParam @NonNull MultipartFile file
    ) {
        return ResponseEntity
                .ok(this.storageService.uploadFile(file, address, id) ? HttpStatus.ACCEPTED
                        : HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id,
                                    @RequestParam("name") String fileName, @RequestParam("postId") Integer postId) {
        return ResponseEntity.ok(this.storageService.deleteFile(fileName, postId, id));
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String fileName) {
        byte[] content = this.storageService.downloadFile(fileName);
        return ResponseEntity
                .ok()
                .contentLength(content.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(new ByteArrayResource(content));
    }
}

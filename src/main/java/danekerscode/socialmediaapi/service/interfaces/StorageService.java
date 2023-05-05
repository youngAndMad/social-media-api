package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.constants.FileAddress;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    Boolean uploadFile(MultipartFile file, FileAddress fileAddress, Integer idToAttach);

    byte[] downloadFile(String fileName);

    Boolean deleteFile(String fileName, Integer ownerId, Integer imageId);
}

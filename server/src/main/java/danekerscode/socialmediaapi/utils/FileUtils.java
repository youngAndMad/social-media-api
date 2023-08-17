package danekerscode.socialmediaapi.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@UtilityClass
@Slf4j
public class FileUtils {

    public static File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getName());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }


}

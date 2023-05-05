package danekerscode.socialmediaapi.service.impl;

import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.PutObjectRequest;

import com.amazonaws.services.s3.model.S3Object;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import com.amazonaws.util.IOUtils;

import danekerscode.socialmediaapi.constants.FileAddress;

import danekerscode.socialmediaapi.service.interfaces.StorageService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;

import static danekerscode.socialmediaapi.utils.Converter.convertMultiPartFileToFile;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Value("${application.bucket.url}")
    private String bucketUrl;

    private final AmazonS3 amazonClient;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Boolean uploadFile(MultipartFile file, FileAddress fileAddress, Integer idToAttach) {
        File fileToUpload = convertMultiPartFileToFile(file);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        amazonClient.putObject(new PutObjectRequest(bucketName, fileName, fileToUpload));

        this.attachImage(fileName, fileAddress, idToAttach);
        fileToUpload.delete();

        return Boolean.TRUE;

    }

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = amazonClient.getObject(bucketName, fileName);

        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        try {
            return IOUtils.toByteArray(inputStream);

        } catch (IOException e) {
            e.printStackTrace();

        }
        return new byte[] {};

    }

    @Override
    public Boolean deleteFile(String fileName, Integer ownerId, Integer imageId) {
        amazonClient.deleteObject(bucketName, fileName);

        return Boolean.TRUE;

    }

    public void attachImage(String fileUrl, FileAddress address, Integer id) {
        String imageUrl = this.bucketUrl + fileUrl;

        switch (address) {
            case USER_AVATAR -> jdbcTemplate.update("update users set image_url = ?  where id = ?;", imageUrl, id);

            case CHANNEL_AVATAR -> jdbcTemplate.update("update channel set image_url = ? where id = ?;", imageUrl, id);

            case POST_IMAGE -> jdbcTemplate.update("insert into image_data(url, post_id) values (?,?);", imageUrl, id);

            default -> throw new EnumConstantNotPresentException(FileAddress.class, address.name());

        }
    }
}
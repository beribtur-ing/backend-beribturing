package ing.beribtur.proxy.minio;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final String bucketName;
    private final int presignedExpirySeconds;

    public MinioService(
            MinioClient minioClient,
            @Value("${minio.bucket.name}") String bucketName,
            @Value("${minio.presigned.expiry:3600}") int presignedExpirySeconds // default 1 hour
    ) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
        this.presignedExpirySeconds = presignedExpirySeconds;
    }

    public String uploadFile(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String extension = (originalFilename != null && originalFilename.contains("."))
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        String uniqueFileName = UUID.randomUUID() + extension;

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(uniqueFileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType() != null ? file.getContentType() : "application/octet-stream")
                        .build()
        );

        return generatePresignedUrl(uniqueFileName);
    }

    public InputStream downloadFile(String fileName) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }

    public void deleteFile(String imageUrlOrKey) throws Exception {
        String fileName = extractFileNameFromUrl(imageUrlOrKey);
        if (fileName != null && !fileName.isBlank()) {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        }
    }

    public String generatePresignedUrl(String fileName) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(fileName)
                        .expiry(presignedExpirySeconds) // e.g., 3600 seconds = 1 hour
                        .build()
        );
    }

    private String extractFileNameFromUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) return null;
        int lastSlashIndex = imageUrl.lastIndexOf('/');
        return (lastSlashIndex >= 0 && lastSlashIndex < imageUrl.length() - 1)
                ? imageUrl.substring(lastSlashIndex + 1)
                : imageUrl;
    }
}

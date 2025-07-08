package ing.beribtur.proxy.minio;

import ing.beribtur.proxy.minio.rdo.MinioFileInfo;
import io.minio.*;
import io.minio.http.Method;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Setter
@Getter
public class MinioService {

    private final MinioClient minioClient;
    private final String bucketName;
    private final int presignedExpirySeconds;

    public MinioService(
            MinioClient minioClient,
            @Value("${minio.bucket.name}") String bucketName,
            @Value("${minio.presigned.expiry:604800}") int presignedExpirySeconds
    ) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
        this.presignedExpirySeconds = presignedExpirySeconds;
    }

    public MinioFileInfo uploadFile(MultipartFile file) throws Exception {
        //
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

        String url = generatePresignedUrl(uniqueFileName);
        return new MinioFileInfo(uniqueFileName, url, LocalDateTime.now().plusSeconds(presignedExpirySeconds));
    }

    public InputStream downloadFile(String fileName) throws Exception {
        //
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }

    public void deleteFile(String imageUrlOrKey) throws Exception {
        //
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
        //
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(fileName)
                        .expiry(presignedExpirySeconds) // e.g., 3600 seconds = 1 hour
                        .build()
        );
    }

    public String extractFileNameFromUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) return null;
        int lastSlashIndex = imageUrl.lastIndexOf('/');
        return (lastSlashIndex >= 0 && lastSlashIndex < imageUrl.length() - 1)
                ? imageUrl.substring(lastSlashIndex + 1)
                : imageUrl;
    }
}

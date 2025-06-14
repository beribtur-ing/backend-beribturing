package ing.beribtur.proxy.minio;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final String bucketName;
    private final String publicUrl;

    public MinioService(MinioClient minioClient,
                        @Value("${minio.bucket.name}") String bucketName,
                        @Value("${minio.publicUrl}") String publicUrl) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
        this.publicUrl = publicUrl;
    }

    public String uploadFile(MultipartFile file) throws Exception {
        //
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        String uniqueFileName = UUID.randomUUID() + extension;

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(uniqueFileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType("binary/octet-stream")
                        .build()
        );

        /*return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(uniqueFileName)
                        .build()
        );*/
        return String.format("%s/%s/%s", publicUrl, bucketName, uniqueFileName);
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

    public void deleteFile(String imageUrl) throws Exception {
        //
        String fileName = extractFileNameFromUrl(imageUrl);
        if (fileName != null && !fileName.isEmpty()) {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
        }
    }

    private String extractFileNameFromUrl(String imageUrl) {
        //
        if (imageUrl == null || imageUrl.isEmpty()) {
            return null;
        }
        int lastSlashIndex = imageUrl.lastIndexOf('/');
        if (lastSlashIndex >= 0 && lastSlashIndex < imageUrl.length() - 1) {
            return imageUrl.substring(lastSlashIndex + 1);
        }
        return imageUrl;
    }
}

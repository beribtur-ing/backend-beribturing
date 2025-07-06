package ing.beribtur.proxy.minio.rdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinioFileInfo {
    //
    private String objectName;
    private String url;
    private LocalDateTime expiresAt;
}

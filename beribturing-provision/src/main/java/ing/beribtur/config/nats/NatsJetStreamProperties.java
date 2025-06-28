package ing.beribtur.config.nats;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "nats.jetstream")
public class NatsJetStreamProperties {
    //
    private List<Stream> streams;

    @Data
    public static class Stream {
        //
        private String name;
        private List<String> subjects;
    }
}

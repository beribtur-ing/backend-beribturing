package ing.beribtur.config.nats;

import com.google.api.gax.rpc.ApiException;
import io.nats.client.JetStreamManagement;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;
import io.nats.client.api.StorageType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class NatsStreamInitializer {
    //
    private final JetStreamManagement jsm;
    private final NatsJetStreamProperties properties;

    public NatsStreamInitializer(io.nats.client.Connection connection,
                                 NatsJetStreamProperties properties) throws Exception {
        this.jsm = connection.jetStreamManagement();
        this.properties = properties;
    }

    @PostConstruct
    public void createStreams() {
        List<NatsJetStreamProperties.Stream> streams = properties.getStreams();
        for (var stream : streams) {
            try {
                StreamConfiguration config = StreamConfiguration.builder()
                        .name(stream.getName())
                        .storageType(StorageType.File)
                        .subjects(stream.getSubjects())
                        .build();

                StreamInfo info = jsm.addStream(config);
                System.out.printf(" Stream created: %s%n", info.getConfiguration().getName());
            } catch (ApiException e) {
                if (e.getMessage().contains("stream name already in use")) {
                    System.out.printf("Stream already exists: %s%n", stream.getName());
                } else {
                    System.err.printf("Failed to create stream [%s]: %s%n", stream.getName(), e.getMessage());
                }
            } catch (Exception e) {
                System.err.printf("Error creating stream [%s]: %s%n", stream.getName(), e.getMessage());
            }
        }
    }
}

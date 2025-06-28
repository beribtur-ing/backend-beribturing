package ing.beribtur.config.nats;

import io.nats.client.Connection;
import io.nats.client.JetStreamManagement;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NatsSubjectManagerService {
    //
    private final JetStreamManagement jsm;

    public NatsSubjectManagerService(Connection connection) throws Exception {
        //
        this.jsm = connection.jetStreamManagement();
    }

    public void addUserSubjectToStream(String streamName, String username) {
        //
        String newSubject = "beribturing.notifications." + username;

        try {
            StreamInfo info = jsm.getStreamInfo(streamName);
            StreamConfiguration oldConfig = info.getConfiguration();
            List<String> currentSubjects = new ArrayList<>(oldConfig.getSubjects());

            if (currentSubjects.contains(newSubject)) {
                System.out.printf("Subject already exists: %s%n", newSubject);
                return;
            }

            currentSubjects.add(newSubject);

            StreamConfiguration newConfig = StreamConfiguration.builder()
                    .name(streamName)
                    .storageType(oldConfig.getStorageType())
                    .subjects(currentSubjects)
                    .build();

            jsm.updateStream(newConfig);
            System.out.printf("Added subject [%s] to stream [%s]%n", newSubject, streamName);
        } catch (Exception e) {
            System.err.printf("Failed to update stream [%s]: %s%n", streamName, e.getMessage());
        }
    }
}

package ing.beribtur.proxy.nats;

import io.nats.client.JetStream;
import io.nats.client.api.PublishAck;
import io.nats.client.impl.NatsMessage;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class NatsPublisherService {
    //
    private final JetStream jetStream;

    public NatsPublisherService(JetStream jetStream) {
        //
        this.jetStream = jetStream;
    }

    public void publish(String subject, String message) {
        //
        try {
            PublishAck ack = jetStream.publish(
                    NatsMessage.builder()
                            .subject(subject)
                            .data(message.getBytes(StandardCharsets.UTF_8))
                            .build()
            );
            System.out.printf("Sent to subject [%s], sequence: %d%n", subject, ack.getSeqno());
        } catch (Exception e) {
            System.err.printf("Failed to publish to [%s]: %s%n", subject, e.getMessage());
        }
    }
}

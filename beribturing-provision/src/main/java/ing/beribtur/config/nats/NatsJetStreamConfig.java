package ing.beribtur.config.nats;


import io.nats.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NatsJetStreamConfig {
    //
    @Value("${nats.url:nats://localhost:4222}")
    private String natsUrl;

    @Bean(destroyMethod = "close")
    public Connection natsConnection() throws IOException, InterruptedException {
        //
        return Nats.connect(natsUrl);
    }

    @Bean
    public JetStream jetStream(Connection natsConnection) throws IOException, JetStreamApiException {
        //
        return natsConnection.jetStream();
    }

    @Bean
    public JetStreamManagement jetStreamManagement(Connection natsConnection) throws IOException, JetStreamApiException {
        //
        return natsConnection.jetStreamManagement();
    }
}

package ing.beribtur.config.nats;

import io.nats.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Duration;

@Configuration
public class NatsJetStreamConfig {
    //
    @Value("${nats.url:nats://localhost:8090}")
    private String natsUrl;

    @Bean(destroyMethod = "close")
    public Connection natsConnection() throws IOException, InterruptedException {
        //
        Options options = new Options.Builder()
                .server(natsUrl)
                .connectionTimeout(Duration.ofSeconds(60))
                .build();

        return Nats.connect(options);
    }

    @Bean
    public JetStream jetStream(Connection natsConnection) throws IOException {
        //
        return natsConnection.jetStream();
    }

    @Bean
    public JetStreamManagement jetStreamManagement(Connection natsConnection) throws IOException {
        //
        return natsConnection.jetStreamManagement();
    }
}

/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package ing.beribtur.beribturing;

import ing.beribtur.config.nats.NatsJetStreamProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"ing.beribtur"}, exclude = MongoAutoConfiguration.class)
@EnableJpaRepositories(basePackages = {"ing.beribtur"})
@EntityScan(basePackages = {"ing.beribtur", "ing.beribtur.config.nats"})
@EnableConfigurationProperties(NatsJetStreamProperties.class)
public class BeribturingBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeribturingBootApplication.class, args);
    }
}

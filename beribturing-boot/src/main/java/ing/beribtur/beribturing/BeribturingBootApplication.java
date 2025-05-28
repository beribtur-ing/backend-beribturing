/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package ing.beribtur.beribturing;

import jakarta.annotation.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "ing.beribtur.beribturing" }, exclude = MongoAutoConfiguration.class)
@EnableJpaRepositories(basePackages = { "ing.beribtur.beribturing" })
@EntityScan(basePackages = { "ing.beribtur.beribturing" })
@Generated("Gen by Vizend Studio v6.1.0")
public class BeribturingBootApplication {
    /* Gen by Vizend Studio v6.1.0 */

    public static void main(String[] args) {
        /* Gen by Vizend Studio v6.1.0 */
        SpringApplication.run(BeribturingBootApplication.class, args);
    }
}

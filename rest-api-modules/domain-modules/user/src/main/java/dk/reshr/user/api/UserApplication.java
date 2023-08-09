package dk.reshr.user.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"dk.reshr.user.api"})
@EnableJpaRepositories(basePackages="dk.reshr.user.api.repository")
@EnableTransactionManagement
@EntityScan(basePackages="dk.reshr.user.api.repository")
public class UserApplication {

 
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

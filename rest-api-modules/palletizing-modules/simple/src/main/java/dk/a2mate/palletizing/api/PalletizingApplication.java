package dk.a2mate.palletizing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import dk.a2mate.palletizing.api.repository.ItemRepository;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"dk.a2mate.palletizing.api"})
@EnableJpaRepositories(basePackages="dk.a2mate.palletizing.api.repository")
@EnableTransactionManagement
@EntityScan(basePackages="dk.a2mate.palletizing.api.repository")
public class PalletizingApplication {

 
    public static void main(String[] args) {
        SpringApplication.run(PalletizingApplication.class, args);
    }
}

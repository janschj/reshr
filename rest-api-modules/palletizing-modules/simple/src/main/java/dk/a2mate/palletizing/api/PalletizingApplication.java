package dk.a2mate.palletizing.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration()
public class PalletizingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalletizingApplication.class, args);
    }
}

package ikus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IkusApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IkusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== APPLICATION STARTED ===");
    }
}
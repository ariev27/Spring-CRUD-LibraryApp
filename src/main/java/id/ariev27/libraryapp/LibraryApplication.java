package id.ariev27.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@Import({DaoSpringBootConfig.class})
@EnableJpaRepositories({"id.ariev27.libraryapp.repository"})
@EntityScan({"id.ariev27.libraryapp.enttity"})
@ComponentScan({"id.ariev27.libraryapp.controller"})
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
}
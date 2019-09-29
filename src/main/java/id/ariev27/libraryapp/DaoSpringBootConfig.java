package id.ariev27.libraryapp;

import id.ariev27.libraryapp.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoSpringBootConfig {

    @Bean
    public TransactionDao transaksi() {
        return new TransactionDaoImpl();
    }

    @Bean
    public BookDao daoBuku() {
        return new BookDaoImpl();
    }

    @Bean
    public StudentDao studentsDao() {
        return new StudentDaoImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

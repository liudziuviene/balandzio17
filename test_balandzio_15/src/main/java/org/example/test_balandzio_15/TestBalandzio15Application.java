package main.java.org.example.test_balandzio_15;

import main.java.org.example.test_balandzio_15.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestBalandzio15Application implements CommandLineRunner {

    private final BookService bookService;

    @Autowired
    public TestBalandzio15Application(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestBalandzio15Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookService.addBooks();
        bookService.runProgram();
    }
}

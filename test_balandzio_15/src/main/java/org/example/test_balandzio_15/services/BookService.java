package main.java.org.example.test_balandzio_15.services;


import main.java.org.example.test_balandzio_15.entities.Book;
import main.java.org.example.test_balandzio_15.enums.Command;
import main.java.org.example.test_balandzio_15.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final Scanner scanner;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.scanner = new Scanner(System.in);
    }

    public void runProgram() {

        boolean isRunning = true;
        while (isRunning) {
            System.out.println();
            System.out.println("Sveiki! Pasirinkite komanda:");
            Command.printCommands();
            String input = scanner.nextLine();
            switch (input.toUpperCase()) {
                case "A" -> addNewBook();
                case "B" -> deleteBook();
                case "C" -> changeLocationOfTheBookById();
                case "D" -> printListOfBooksByGenre();
                case "E" -> printAllFantasyBooksReleasedThisYear();
                case "F" -> printAllBooks();
                case "Z" -> isRunning = false;
                default -> System.out.println("Netinkama komanda");
            }
        }
    }

    private void printAllBooks() {

        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void printAllFantasyBooksReleasedThisYear() {

        int currentYear = Year.now().getValue();
        List<Book> fantasyBooksThisYear = bookRepository.findByGenreAndYears("fantastine", currentYear);
        if (fantasyBooksThisYear.isEmpty()) {
            System.out.println("Knygu nerasta");
        } else {
            fantasyBooksThisYear.forEach(System.out::println);
        }
    }

    private void printListOfBooksByGenre() {

        System.out.println("Parasykite norimu rasti knygu zanra");
        String input = scanner.nextLine();
        List<Book> booksByGenre = bookRepository.findByGenre(input);
        if (booksByGenre.isEmpty()) {
            System.out.println("Knygu nerasta");
        } else {
            booksByGenre.forEach(System.out::println);
        }
    }

    private void changeLocationOfTheBookById() {

        System.out.println("Iveskite knygos ID");
        Long input = Long.parseLong(scanner.nextLine());

        System.out.println("Iveskite nauja knygos lokacija");
        String newLocation = scanner.nextLine();

        bookRepository.findById(input).ifPresentOrElse(book -> {
            book.setLocation(newLocation);
            bookRepository.save(book);
            System.out.println("Knygos lokacija pakeista");
        }, () -> {
            System.out.println("Knyga " + input + " nerasta");
        });
    }

    private void deleteBook() {

        System.out.println("Iveskite knygos ID");
        Long input = Long.parseLong(scanner.nextLine());

        boolean exists = bookRepository.existsById(input);
        if (exists) {
            bookRepository.deleteById(input);
            System.out.println("Knyga " + input + " pasalinta");
        } else {
            System.out.println("Knyga " + input + " nerasta");
        }
    }


    private void addNewBook() {
        Book book = new Book();
        System.out.println("Iveskite pavadinima");
        book.setTitle(scanner.nextLine());
        System.out.println("Iveskite autoriu");
        book.setAuthor(scanner.nextLine());
        System.out.println("Iveskite leidimo metus");
        book.setYears(Integer.valueOf(scanner.nextLine()));
        System.out.println("Iveskite zanra");
        book.setGenre(scanner.nextLine());
        System.out.println("Iveskite knygos buvimo vieta");
        book.setLocation(scanner.nextLine());

        System.out.println("Knyga prideta");
        bookRepository.saveAndFlush(book);
    }

    public void addBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("title1", "author1", 2024, "fantastine", "lentyna1"));
        books.add(new Book("title10", "author1", 2024, "fantastine", "lentyna1"));
        books.add(new Book("title11", "author1", 2024, "zanras2", "lentyna1"));
        books.add(new Book("title12", "author2", 2020, "zanras2", "lentyna3"));
        books.add(new Book("title3", "author3", 2010, "zanras3", "lentyna2"));
        books.add(new Book("title4", "author4", 2024, "zanras3", "lentyna4"));
        books.add(new Book("title5", "author5", 2010, "zanras5", "lentyna3"));
        books.add(new Book("title6", "author6", 2020, "fantastine", "lentyna2"));
        books.add(new Book("title7", "author7", 2024, "zanras3", "lentyna3"));

        bookRepository.saveAllAndFlush(books);
    }
}

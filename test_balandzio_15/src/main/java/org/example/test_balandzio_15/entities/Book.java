package main.java.org.example.test_balandzio_15.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private Integer years;
    @NonNull
    private String genre;
    @NonNull
    private String location;

}

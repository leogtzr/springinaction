package com.books.web;

import com.books.data.BookRepository;
import com.books.domain.Book;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;

@RestController
public class DummyController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/getall")
    public ResponseEntity<String> call() {
        return ResponseEntity.ok().body(restTemplate.getForObject("http://localhost:8080/books", String.class)
        );
    }

    @GetMapping("/create")
    public Book createBook() {
        // final Book book = new Book(RandomUtils.nextLong(1, 1000), RandomStringUtils.randomAlphabetic(30));
        final Book book = new Book(1L, RandomStringUtils.randomAlphabetic(30));
        return restTemplate.postForObject("http://localhost:8080/books", book, Book.class);
    }

    @PostMapping("/crea")
    public Book crearBook(final Book book) {
        final ResponseEntity<Book> responseEntity = restTemplate.postForEntity(
                "http://localhost:8080/books", book, Book.class)
                ;
        return responseEntity.getBody();
    }

    @GetMapping("/shit")
    @ResponseBody
    public void shit() {
        final Traverson traverson = new Traverson(URI.create("http://localhost:8080/books"), MediaTypes.HAL_JSON);
        final ParameterizedTypeReference<Resources<Book>> bookType = new ParameterizedTypeReference<Resources<Book>>() {};
        final Resources<Book> bookRes = traverson.follow("self").toObject(bookType);
        final Collection<Book> books = bookRes.getContent();
        books.forEach(System.out::println);

        System.out.println(String.format("Collection size: %d", books.size()));
    }

}

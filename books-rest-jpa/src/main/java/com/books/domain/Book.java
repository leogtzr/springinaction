package com.books.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
@RestResource(rel = "books", path = "libros")
public class Book {
    @Id
    private long id;
    private String title;
}

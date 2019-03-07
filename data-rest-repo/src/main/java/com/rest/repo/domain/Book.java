package com.rest.repo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class Book {

    @Id
    private long id;
    private String title;

}

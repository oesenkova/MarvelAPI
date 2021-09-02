package com.oesenkova.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int digitalId;

    private String title;

    private int issueNumber;

    private String variantDescription;

    private String description;

    private String isbn;

    private String upc;

    private String diamondCode;

    private String ean;

    private String issn;

    private String format;

    private int pageCount;

    private String resourceURI;

    private Thumbnail thumbnail;
}

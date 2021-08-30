package com.oesenkova.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int digitalId;

    private String title;

    private int issueNumber;

    private String variantDescription;

    private String description;

    private Date modified;

    private String isbn;

    private String upc;

    private String diamondCode;

    private String ean;

    private String issn;

    private String format;

    private int pageCount;

    private String resourceURI;
}

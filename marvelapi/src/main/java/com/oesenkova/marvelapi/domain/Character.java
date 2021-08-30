package com.oesenkova.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String resourceURI;

    private Date modified;
}

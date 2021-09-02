package com.oesenkova.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thumbnail implements Serializable {
    private String path;
    private String extension;

    public String getResourceUrl() {
        return path + "." + extension;
    }
}

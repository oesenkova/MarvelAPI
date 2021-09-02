package com.oesenkova.marvelapi.service.interfaces;

import com.oesenkova.marvelapi.domain.Thumbnail;

import java.net.MalformedURLException;

public interface ResourceClient {
    byte[] getResource(Thumbnail thumbnail);
}

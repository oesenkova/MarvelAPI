package com.oesenkova.marvelapi.http;

import com.oesenkova.marvelapi.domain.Thumbnail;
import com.oesenkova.marvelapi.service.interfaces.ResourceClient;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class ResourceClientImpl implements ResourceClient {
    @Override
    public byte[] getResource(Thumbnail thumbnail) {
        try {
            URL url = new URL(thumbnail.getResourceUrl());
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            InputStream inputStream = url.openStream();
            int n = 0;
            byte[] buffer = new byte[1024];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}

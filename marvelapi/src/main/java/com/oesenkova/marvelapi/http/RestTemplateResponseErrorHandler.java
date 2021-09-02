package com.oesenkova.marvelapi.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oesenkova.marvelapi.domain.exceptoins.ExternalServiceException;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

        return (
                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @SneakyThrows
    @Override
    public void handleError(ClientHttpResponse httpResponse) {
        String body = new String(httpResponse.getBody().readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage message = mapper.readValue(body, ErrorMessage.class);
        throw new ExternalServiceException(httpResponse.getRawStatusCode(), message.getStatus());
    }
}

package com.oesenkova.marvelapi.http;

import com.oesenkova.marvelapi.DTO.MarvelData;
import com.oesenkova.marvelapi.DTO.MarvelResponse;
import com.oesenkova.marvelapi.domain.BaseQuery;
import com.oesenkova.marvelapi.domain.PageResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
class MarvelRestClient {
    @Value("${marvelapi.publickey}")
    private String publicKey;

    @Value("${marvelapi.privatekey}")
    private String privateKey;

    @Value("${marvelapi.baseurl}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    //Возвращвет искомую сущность
    public <T> T get(String route, Class<T> genericClass) {
        return execute(route, "", genericClass).getData().getResults().get(0);
    }

    //возвращвет постраничный список сущностей
    public <T,V extends BaseQuery> PageResult<T> getPage(String route, V query, Class<T> genericClass) {
        String queryParams = QueryMapper.map(query);
        MarvelResponse<T> response = execute(route, queryParams, genericClass);
        MarvelData<T> data = response.getData();
        return new PageResult<T>(data.getTotal(), data.getResults());
    }

    //базовый метод вызова API
    private  <T> MarvelResponse<T> execute(String route, String queryParams, Class<T> genericClass) {
        String url = generateUrl(route, queryParams);
        ResponseEntity<MarvelResponse<T>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                ParameterizedTypeReference.forType(ResolvableType.forClassWithGenerics(MarvelResponse.class, genericClass).getType()));
        return response.getBody();
    }

    private String generateUrl(String route, String queryParams) {
        long timeStamp = System.currentTimeMillis();
        String hash = generateHash(timeStamp);
        return baseUrl + "/" + route + "?apikey=" + publicKey + "&ts=" + timeStamp + "&hash=" + hash + "&" + queryParams;
    }

    private String generateHash(long timeStamp) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String stringToHash = timeStamp + privateKey + publicKey;

            byte[] hash = md.digest(stringToHash.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return digest;
    }
}

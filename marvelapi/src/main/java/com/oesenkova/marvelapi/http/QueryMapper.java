package com.oesenkova.marvelapi.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oesenkova.marvelapi.domain.BaseQuery;

import java.util.Map;
import java.util.stream.Collectors;

public class QueryMapper {
    public static <T extends BaseQuery> String map(T query) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.convertValue(query, new TypeReference<>() {
        });
        return map.entrySet().stream().filter(x -> x != null && x.getValue() != null)
                .map(x -> x.getKey() + "=" + x.getValue()).collect(Collectors.joining("&"));
    }
}

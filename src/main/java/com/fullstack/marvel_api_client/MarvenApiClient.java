package com.fullstack.marvel_api_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HexFormat;
import java.util.List;

public class MarvenApiClient {

    @Value("${api.param.apiKey}")
    String apiKey="ba17efd9c5588fd8b45bc4884a6660fd";

    @Value("${api.param.key}")
    String key="dd74020eeb1645e669c7449be98f56ba802a28bc";

    @Value("${api.param.ts}")
    long ts= 30;

    final String BASE_PATH = "https://gateway.marvel.com/v1/public/characters";
    final String HASH_PARAM = "&hash=";
    final String TS_PARAM = "?ts=";
    final String API_KEY_PARAM = "&apikey=";
    final String SLASH_CHAR = "/";



    List<CharacterDTO> getAllCharacters(){
        RestTemplate restTemplate = new RestTemplate();
        String uri = buildUri(null);
        System.out.println("Generated URI : " + uri);

        HttpEntity<MarvelApiResponse> entity = restTemplate.getForEntity(uri, MarvelApiResponse.class);

        getTimeCalledApi(entity.getHeaders());

        System.out.println("Generated : " + entity);

        return entity.getBody().getData().getResults();
    }

    CharacterDTO getCharacterById(Long characterId) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = buildUri(characterId);
        System.out.println("Generated URI : " + uri);

        HttpEntity<MarvelApiResponse> entity = restTemplate.getForEntity(uri, MarvelApiResponse.class);
        MarvelApiResponse body = entity.getBody();

        getTimeCalledApi(entity.getHeaders());

        System.out.println("Generated : " + body.getData().getResults().get(0));
        return body.getData().getResults().get(0);
    }

    void getTimeCalledApi(HttpHeaders headers) {
        long milliSeconds = headers.getDate();
        System.out.println("Response date : " + new Date(milliSeconds));
    }

    String buildUri(Long characterId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_PATH);
        if (characterId != null) {
            stringBuilder.append(SLASH_CHAR).append(characterId);
        }
        return stringBuilder.append(TS_PARAM).append(ts).
                append(API_KEY_PARAM).append(this.apiKey).append(HASH_PARAM).append(generateHash()).toString();
    }

    String generateHash(){
        String apiKey = this.apiKey;
        String key = this.key;
        long ts = this.ts;
        String output = new StringBuilder().append(ts).append(key).append(apiKey).toString();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(output.getBytes());
        byte[] digest = md.digest();
        HexFormat hexFormat = HexFormat.of();
        String outputHex = hexFormat.formatHex(digest);
        System.out.println("Generated : " + outputHex);
        return outputHex;
    }

}

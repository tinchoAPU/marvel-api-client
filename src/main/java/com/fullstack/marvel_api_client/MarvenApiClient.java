package com.fullstack.marvel_api_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class MarvenApiClient {

    @Value("${api.param.apiKey}")
    String apiKey="ba17efd9c5588fd8b45bc4884a6660fd";

    @Value("${api.param.key}")
    String key="dd74020eeb1645e669c7449be98f56ba802a28bc";

    @Value("${api.param.ts}")
    long ts= 30;

    final String BASE_PATH = "https://gateway.marvel.com/v1/public/characters";

    void invoqueAllCharacters(){
        RestTemplate restTemplate = new RestTemplate();
        String uri = new StringBuilder().append(BASE_PATH).append("?ts=").append(ts).
                append("&apikey=").append(this.apiKey).append("&hash=").append(generateHash()).toString();
        System.out.println("Generated URI : " + uri);
        MarvelApiResponse result = restTemplate.getForObject(uri, MarvelApiResponse.class);
        System.out.println("Generated : " + result);
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

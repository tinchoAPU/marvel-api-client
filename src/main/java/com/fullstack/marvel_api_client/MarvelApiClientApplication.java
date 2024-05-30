package com.fullstack.marvel_api_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarvelApiClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelApiClientApplication.class, args);
		MarvenApiClient mvCliente = new MarvenApiClient();
		mvCliente.getAllCharacters();
		mvCliente.getCharacterById(1009146L).toString();
	}

}

package de.neuefische.rest_client.service;

import de.neuefische.rest_client.model.RickAndMortyApiResponse;
import de.neuefische.rest_client.model.RickAndMortyCharacter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RickAndMortyService {

    private RestClient restClient = RestClient.builder()
            .baseUrl("https://rickandmortyapi.com/api")
            .build();

    public RickAndMortyApiResponse getAllRickAndMortyCharacters() {
        return restClient.get()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyApiResponse.class);
    }

    public RickAndMortyCharacter getCharacterById(String id) {
        return restClient.get()
                .uri("/character/" + id)
                .retrieve()
                .body(RickAndMortyCharacter.class);
    }

    public RickAndMortyApiResponse getCharacterByStatus(String status) {
        return restClient.get()
                .uri("/character?status=" + status)
                .retrieve()
                .body(RickAndMortyApiResponse.class);
    }
}

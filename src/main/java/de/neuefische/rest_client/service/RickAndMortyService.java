package de.neuefische.rest_client.service;

import de.neuefische.rest_client.exception.InvalidIdException;
import de.neuefische.rest_client.model.RickAndMortyApiResponse;
import de.neuefische.rest_client.model.RickAndMortyCharacter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Service
public class RickAndMortyService {

    private final RestClient restClient;

    public RickAndMortyService(@Value("${basic.url}") String basicUrl) {
        this.restClient = RestClient.create(basicUrl);
    }

//    private RestClient restClient = RestClient.builder()
//            .baseUrl("https://rickandmortyapi.com/api")
//            .build();

    public RickAndMortyApiResponse getAllRickAndMortyCharacters() {
        return restClient.get()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyApiResponse.class);
    }

    public RickAndMortyCharacter getCharacterById(String id) {
        if(Integer.parseInt(id) < 825){
            return restClient.get()
                    .uri("/character/" + id)
                    .retrieve()
                    .body(RickAndMortyCharacter.class);
        }
        else
            throw new InvalidIdException("Invalid ID");
    }

    public RickAndMortyApiResponse getCharacterByStatus(String status) {
        return restClient.get()
                .uri("/character?status=" + status)
                .retrieve()
                .body(RickAndMortyApiResponse.class);
    }

    public RickAndMortyApiResponse getSpeciesStats(String species) {
        return restClient.get()
                .uri("/character?species=" + species)
                .retrieve()
                .body(RickAndMortyApiResponse.class);
    }
}

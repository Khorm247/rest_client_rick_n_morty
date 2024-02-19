package de.neuefische.rest_client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RickAndMortyCharacter {
    private String id;
    private String name;
    private String status;
    private String species;
}

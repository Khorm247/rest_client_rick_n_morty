package de.neuefische.rest_client.controller;

import de.neuefische.rest_client.model.InfoClass;
import de.neuefische.rest_client.model.RickAndMortyCharacter;
import lombok.RequiredArgsConstructor;
import de.neuefische.rest_client.model.RickAndMortyApiResponse;
import org.springframework.web.bind.annotation.*;
import de.neuefische.rest_client.service.RickAndMortyService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final RickAndMortyService service;

    @GetMapping("/rick")
    public RickAndMortyApiResponse getAllRickAndMortyChars(){
        return service.getAllRickAndMortyCharacters();
    }

    @GetMapping("/morty")
    public String mortyIsHere(){
        return "Morty is here!";
    }

    @GetMapping("/characters/{id}")
    public RickAndMortyCharacter getRickById(@PathVariable String id){
        return service.getCharacterById(id);
    }

    @GetMapping("/characters")
    public RickAndMortyApiResponse getRickByStatus(@RequestParam String status){
        return service.getCharacterByStatus(status);
    }

    @GetMapping("/species-stats")
    public String getSpeciesStats(@RequestParam String species){
        RickAndMortyApiResponse speciesStats = service.getSpeciesStats(species);
        return speciesStats.getInfo().toString();
    }
}

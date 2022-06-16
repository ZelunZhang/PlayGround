package io.zelun.pokeapiapp.controllers;

import io.zelun.pokeapiapp.Services.PokemonService;
import io.zelun.pokeapiapp.dto.PokemonServiceResponse;
import io.zelun.pokeapiapp.model.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokeApiController {
    @Autowired
    private PokemonService pokemonService;

    @RequestMapping("/pokemon/{id}")
    public PokemonServiceResponse getPokenmonById(@PathVariable String id) {
        return pokemonService.getPokenmonById(id);
    }

    @RequestMapping("/pokemon/translated/{id}")
    public PokemonServiceResponse getPokenmonTranslatedById(@PathVariable String id) {
        return pokemonService.getPokenmonTranslatedById(id);
    }

    @RequestMapping("/info/id/{id}")
    public Info getInfoById(@PathVariable String id) {
        return pokemonService.getInfoById(id);
    }

    @RequestMapping("/info/name/{name}")
    public Info getInfoByName(@PathVariable String name) {
        return pokemonService.getInfoByName(name);
    }


}

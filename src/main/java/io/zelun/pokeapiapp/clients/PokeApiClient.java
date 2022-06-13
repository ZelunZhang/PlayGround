package io.zelun.pokeapiapp.clients;

import io.zelun.pokeapiapp.dto.pokemonApi.PokemonApiResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "pokemonFeignClient", url = "${pokemon.base.url}")
public interface PokeApiClient {

    @Cacheable(cacheNames = "pokemon-api-cache")
    @GetMapping("/pokemon-species/{id}")
    PokemonApiResponse getPokenmonById (@PathVariable("id") String id);
}

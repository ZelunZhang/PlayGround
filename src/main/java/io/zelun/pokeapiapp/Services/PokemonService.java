package io.zelun.pokeapiapp.Services;

import io.zelun.pokeapiapp.dto.PokemonServiceResponse;
import io.zelun.pokeapiapp.dto.pokemonApi.PokemonApiResponse;

public interface PokemonService {
    PokemonServiceResponse getPokenmonById (String id);

    PokemonServiceResponse getPokenmonTranslatedById (String id);
}

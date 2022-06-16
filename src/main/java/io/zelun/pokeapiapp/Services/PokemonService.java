package io.zelun.pokeapiapp.Services;

import io.zelun.pokeapiapp.dto.PokemonServiceResponse;
import io.zelun.pokeapiapp.model.Info;

public interface PokemonService {
    PokemonServiceResponse getPokenmonById(String id);

    PokemonServiceResponse getPokenmonTranslatedById(String id);

    Info getInfoById(String id);

    Info getInfoByName(String name);
}

package io.zelun.pokeapiapp.Services;

import io.zelun.pokeapiapp.clients.PokeApiClient;
import io.zelun.pokeapiapp.clients.TranslationApiClient;
import io.zelun.pokeapiapp.dto.PokemonServiceResponse;
import io.zelun.pokeapiapp.dto.pokemonApi.FlavorText;
import io.zelun.pokeapiapp.dto.pokemonApi.PokemonApiResponse;
import io.zelun.pokeapiapp.dto.translationApi.TranslationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.zelun.pokeapiapp.utils.Constants.DESCRIPTION_LANGUAGE;
import static io.zelun.pokeapiapp.utils.Constants.HABITAT_FOR_YODA;

@Slf4j
@Service
public class PokemonServiceImpl implements PokemonService {
    @Autowired
    private PokeApiClient pokeApiClient;

    @Autowired
    private TranslationApiClient translationApiClient;

    @Override
    public PokemonServiceResponse getPokenmonById(String id) {
        return getPokemonServiceResponse(pokeApiClient.getPokenmonById(id));
    }

    @Override
    public PokemonServiceResponse getPokenmonTranslatedById(String id) {
        PokemonServiceResponse pokemonServiceResponse =
                getPokemonServiceResponse(pokeApiClient.getPokenmonById(id));
        String translatedDescripton = getTranslatedDescription(pokemonServiceResponse);
        return PokemonServiceResponse.builder()
                .name(pokemonServiceResponse.getName())
                .description(translatedDescripton)
                .habitat(pokemonServiceResponse.getHabitat())
                .isLengendary(pokemonServiceResponse.isLengendary())
                .build();
    }

    private String getTranslatedDescription(PokemonServiceResponse pokemonServiceResponse) {
        if (pokemonServiceResponse.getDescription() == null) {
            return null;
        }
        try {
            if (HABITAT_FOR_YODA.equals(pokemonServiceResponse.getHabitat()) || pokemonServiceResponse.isLengendary()) {
                return translationApiClient.translateByYoda(TranslationRequest.builder()
                        .text(pokemonServiceResponse.getDescription())
                        .build()).getContents().getTranslated();
            }
            return translationApiClient.translateByShakespeare(TranslationRequest.builder()
                    .text(pokemonServiceResponse.getDescription())
                    .build()).getContents().getTranslated();
        } catch (Exception e) {
            log.warn("Exception {} is captured, hence fallback to use the original description.", e.getMessage());
            return pokemonServiceResponse.getDescription();
        }
    }

    private PokemonServiceResponse getPokemonServiceResponse(PokemonApiResponse pokemonApiResponse) {
        if (pokemonApiResponse == null) {
            return null;
        }
        return PokemonServiceResponse.builder()
                .name(pokemonApiResponse.getName())
                .description(getDescription(pokemonApiResponse.getFlavorTextEntries()))
                .habitat(pokemonApiResponse.getHabitat().getName())
                .isLengendary(pokemonApiResponse.isLegendary())
                .build();
    }

    private String getDescription(List<FlavorText> entries) {
        if (entries == null || entries.size() == 0) {
            return "";
        }
        return entries.stream()
                .filter(e -> DESCRIPTION_LANGUAGE.equals(e.getLanguage().getName()))
                .findFirst()
                .orElse(FlavorText.builder().flavorText("").build())
                .getFlavorText()
                .replaceAll("\\n", " ")
                .replaceAll("\\f", " ");
    }
}

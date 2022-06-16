package io.zelun.pokeapiapp.Services;

import io.zelun.pokeapiapp.Repo.InfoRepo;
import io.zelun.pokeapiapp.clients.PokeApiClient;
import io.zelun.pokeapiapp.clients.TranslationApiClient;
import io.zelun.pokeapiapp.dto.PokemonServiceResponse;
import io.zelun.pokeapiapp.dto.pokemonApi.FlavorText;
import io.zelun.pokeapiapp.dto.pokemonApi.Habitat;
import io.zelun.pokeapiapp.dto.pokemonApi.Language;
import io.zelun.pokeapiapp.dto.pokemonApi.PokemonApiResponse;
import io.zelun.pokeapiapp.dto.translationApi.Contents;
import io.zelun.pokeapiapp.dto.translationApi.TranslationRequest;
import io.zelun.pokeapiapp.dto.translationApi.TranslationResponse;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokemonServiceTest {
    @Mock
    private PokeApiClient pokeApiClient;

    @Mock
    private TranslationApiClient translationApiClient;

    @Mock
    private InfoRepo infoRepo;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @BeforeAll
    public void beforeAll() {
        when(infoRepo.findInfoById(any())).thenReturn(null);
        when(infoRepo.save(any())).thenReturn(null);
    }

    @Test
    public void testGetPokemonById() {
        String testName = "testName";
        String testHabitat = "cave";
        String testFlavorText = "Some test flavor text";
        String testLanguage = "en";
        boolean isLegendary = true;
        PokemonApiResponse pokemonApiResponse = PokemonApiResponse.builder()
                .name(testName)
                .habitat(Habitat.builder()
                        .name(testHabitat)
                        .build())
                .isLegendary(isLegendary)
                .flavorTextEntries(Arrays.asList(FlavorText.builder()
                        .flavorText(testFlavorText)
                        .language(Language.builder()
                                .name(testLanguage)
                                .build())
                        .build()))
                .build();
        when(pokeApiClient.getPokenmonById(any())).thenReturn(pokemonApiResponse);

        PokemonServiceResponse pokemonServiceResponse = pokemonService.getPokenmonById("testName");
        assertEquals(testName, pokemonServiceResponse.getName());
        assertEquals(testHabitat, pokemonServiceResponse.getHabitat());
        assertEquals(testFlavorText, pokemonServiceResponse.getDescription());
        assertEquals(isLegendary, pokemonServiceResponse.isLengendary());
    }

    @Test
    public void testGetPokemonTranslatedByYoda() {
        String testNameForYoda = "yoda";
        String testHabitatForYoda = "cave";
        boolean isLegendaryForYoda = true;
        String testFlavorText = "Some flavor text";
        String testLanguage = "en";
        String yodaTranslatedText = "Some yoda translated text";

        PokemonApiResponse pokemonApiResponseForYoda = PokemonApiResponse.builder()
                .name(testNameForYoda)
                .habitat(Habitat.builder()
                        .name(testHabitatForYoda)
                        .build())
                .isLegendary(isLegendaryForYoda)
                .flavorTextEntries(Arrays.asList(FlavorText.builder()
                        .flavorText(testFlavorText)
                        .language(Language.builder()
                                .name(testLanguage)
                                .build())
                        .build()))
                .build();

        when(pokeApiClient.getPokenmonById(any())).thenReturn(pokemonApiResponseForYoda);
        when(translationApiClient.translateByYoda(any(TranslationRequest.class)))
                .thenReturn(TranslationResponse.builder()
                        .contents(Contents.builder()
                                .translated(yodaTranslatedText)
                                .build())
                        .build());

        PokemonServiceResponse pokemonServiceResponse = pokemonService.getPokenmonTranslatedById(testNameForYoda);
        assertEquals(testNameForYoda, pokemonServiceResponse.getName());
        assertEquals(testHabitatForYoda, pokemonServiceResponse.getHabitat());
        assertEquals(yodaTranslatedText, pokemonServiceResponse.getDescription());
        assertEquals(isLegendaryForYoda, pokemonServiceResponse.isLengendary());
    }

    @Test
    public void testGetPokemonTranslatedByShakespeare() {
        String testNameForShakespeare = "Shakespeare";
        String testHabitatForShakespeare = "non-cave";
        boolean isLegendaryForShakespeare = false;
        String testFlavorText = "Some flavor text";
        String testLanguage = "en";
        String shakespeareTranslatedText = "Some Shakespeare translated text";

        PokemonApiResponse pokemonApiResponseForShakespeare = PokemonApiResponse.builder()
                .name(testNameForShakespeare)
                .habitat(Habitat.builder()
                        .name(testHabitatForShakespeare)
                        .build())
                .isLegendary(isLegendaryForShakespeare)
                .flavorTextEntries(Arrays.asList(FlavorText.builder()
                        .flavorText(testFlavorText)
                        .language(Language.builder()
                                .name(testLanguage)
                                .build())
                        .build()))
                .build();

        when(pokeApiClient.getPokenmonById(any())).thenReturn(pokemonApiResponseForShakespeare);
        when(translationApiClient.translateByShakespeare(any(TranslationRequest.class)))
                .thenReturn(TranslationResponse.builder()
                        .contents(Contents.builder()
                                .translated(shakespeareTranslatedText)
                                .build())
                        .build());

        PokemonServiceResponse pokemonServiceResponse = pokemonService.getPokenmonTranslatedById(testNameForShakespeare);
        assertEquals(testNameForShakespeare, pokemonServiceResponse.getName());
        assertEquals(testHabitatForShakespeare, pokemonServiceResponse.getHabitat());
        assertEquals(shakespeareTranslatedText, pokemonServiceResponse.getDescription());
        assertEquals(isLegendaryForShakespeare, pokemonServiceResponse.isLengendary());
    }

    @Test
    public void testGetPokemonTranslatedWhenExceptionHappens() {
        String testName = "testName";
        String testHabitat = "cave";
        String testFlavorText = "Some test flavor text";
        String testLanguage = "en";
        boolean isLegendary = true;

        PokemonApiResponse pokemonApiResponseForShakespeare = PokemonApiResponse.builder()
                .name(testName)
                .habitat(Habitat.builder()
                        .name(testHabitat)
                        .build())
                .isLegendary(isLegendary)
                .flavorTextEntries(Arrays.asList(FlavorText.builder()
                        .flavorText(testFlavorText)
                        .language(Language.builder()
                                .name(testLanguage)
                                .build())
                        .build()))
                .build();

        when(pokeApiClient.getPokenmonById(any())).thenReturn(pokemonApiResponseForShakespeare);
        lenient().when(translationApiClient.translateByShakespeare(any(TranslationRequest.class)))
                .thenThrow(new RuntimeException("boom!"));

        PokemonServiceResponse pokemonServiceResponse = pokemonService.getPokenmonTranslatedById(testName);
        assertEquals(testName, pokemonServiceResponse.getName());
        assertEquals(testHabitat, pokemonServiceResponse.getHabitat());
        assertEquals(testFlavorText, pokemonServiceResponse.getDescription());
        assertEquals(isLegendary, pokemonServiceResponse.isLengendary());
    }
}
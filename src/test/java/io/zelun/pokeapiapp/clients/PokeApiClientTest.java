package io.zelun.pokeapiapp.clients;

import feign.FeignException;
import io.zelun.pokeapiapp.dto.pokemonApi.PokemonApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import wiremock.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
class PokeApiClientTest {

    private final String SUCCESS_TEST_ID = "mewtwo";
    private final String NOT_FOUND_TEST_ID = "notFound";
    @Autowired
    PokeApiClient pokeApiClient;

    @Test
    public void test_Client_With_Valid_Response() throws Exception {
        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/pokemon/" + SUCCESS_TEST_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/mewtwo.json"))));

        PokemonApiResponse pokeApiResponse = pokeApiClient.getPokenmonById(SUCCESS_TEST_ID);
        assertEquals(pokeApiResponse.getName(), SUCCESS_TEST_ID);
    }

    @Test
    public void test_Client_With_404_Response(){
        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/pokemon/" + NOT_FOUND_TEST_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("not found")));
        //Exception is thrown by FeignClient.
        assertThrows(FeignException.class, () -> {
            pokeApiClient.getPokenmonById(NOT_FOUND_TEST_ID);;
        });
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}
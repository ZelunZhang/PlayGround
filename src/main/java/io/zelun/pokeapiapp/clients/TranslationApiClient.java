package io.zelun.pokeapiapp.clients;

import io.zelun.pokeapiapp.dto.pokemonApi.PokemonApiResponse;
import io.zelun.pokeapiapp.dto.translationApi.TranslationRequest;
import io.zelun.pokeapiapp.dto.translationApi.TranslationResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient (name = "translationFeignClient", url = "${translation.base.url}")
public interface TranslationApiClient {

    @Cacheable(cacheNames = "transation-yoda-cache")
    @PostMapping("/yoda")
    TranslationResponse translateByYoda(@RequestBody TranslationRequest translationRequest);

    @Cacheable(cacheNames = "transation-shakespeare-cache")
    @PostMapping("/shakespeare")
    TranslationResponse translateByShakespeare(@RequestBody TranslationRequest translationRequest);

}

package io.zelun.pokeapiapp.dto.pokemonApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PokemonApiResponse {

    private String name;

    @JsonProperty("flavor_text_entries")
    private List<FlavorText> flavorTextEntries;

    private Habitat habitat;

    @JsonProperty("is_legendary")
    private boolean isLegendary;
}

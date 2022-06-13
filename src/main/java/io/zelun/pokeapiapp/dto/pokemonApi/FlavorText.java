package io.zelun.pokeapiapp.dto.pokemonApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class FlavorText {
    @JsonProperty("flavor_text")
    private String flavorText;
    private Language language;
}

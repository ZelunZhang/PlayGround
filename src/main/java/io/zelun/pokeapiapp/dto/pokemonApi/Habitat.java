package io.zelun.pokeapiapp.dto.pokemonApi;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Habitat {
    private String name;
    private String url;
}

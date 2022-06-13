package io.zelun.pokeapiapp.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PokemonServiceResponse {
    private String name;
    private String description;
    private String habitat;
    private boolean isLengendary;
}

package io.zelun.pokeapiapp.dto.translationApi;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class TranslationResponse {
    private Contents contents;
}

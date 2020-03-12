package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Meta {

    @JsonProperty("uid")
    private String uid;
}

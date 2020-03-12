package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Entity {

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("data")
    private Data data;
}

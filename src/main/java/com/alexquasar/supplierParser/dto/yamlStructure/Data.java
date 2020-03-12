package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Data {

    @JsonProperty("data-id")
    private String dataId;

    @JsonProperty("integrity-fixed")
    private Boolean integrityFixed;

    @JsonProperty("section")
    private Set<Section> sections;
}

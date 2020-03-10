package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Data {

    @JsonProperty("data-id")
    private String dataId;

    @JsonProperty("integrity-fixed")
    private Boolean integrityFixed;

    @JsonProperty("section")
    private List<Section> sections;
}

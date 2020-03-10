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
public class Section {

    @JsonProperty("section_id")
    private String section_id;

    @JsonProperty("integrity-fixed")
    private Boolean integrityFixed;

    @JsonProperty("section_type")
    private String section_type;

    @JsonProperty("list")
    private List<SectionList> lists;
}

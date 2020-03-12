package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Section {

    @JsonProperty("section_id")
    private String section_id;

    @JsonProperty("integrity-fixed")
    private Boolean integrityFixed;

    @JsonProperty("section_type")
    private String section_type;

    @JsonProperty("list")
    private Set<SectionList> lists;
}

package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SectionList {

    @JsonProperty("list-id")
    private String listId;

    @JsonProperty("list-type")
    private String listType;

    @JsonProperty("group")
    private Set<Group> groups;
}

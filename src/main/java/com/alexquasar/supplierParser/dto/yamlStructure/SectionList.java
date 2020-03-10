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
public class SectionList {

    @JsonProperty("list-id")
    private String listId;

    @JsonProperty("list-type")
    private String listType;

    @JsonProperty("group")
    private List<Group> groups;
}

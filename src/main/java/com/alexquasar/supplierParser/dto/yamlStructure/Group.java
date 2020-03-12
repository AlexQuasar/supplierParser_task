package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Group {

    @JsonProperty("group-id")
    private String groupId;

    @JsonProperty("show_as")
    private String show_as;

    @JsonProperty("group-num")
    private Integer groupNum;

    @JsonProperty("item")
    private Set<Item> items;
}

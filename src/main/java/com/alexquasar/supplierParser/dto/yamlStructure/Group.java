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
public class Group {

    @JsonProperty("group-id")
    private String groupId;

    @JsonProperty("show_as")
    private String show_as;

    @JsonProperty("group-num")
    private Integer groupNum;

    @JsonProperty("item")
    private List<Item> items;
}

package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;
import java.util.Set;

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
    private Set<Item> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupId, group.groupId) &&
                Objects.equals(show_as, group.show_as) &&
                Objects.equals(groupNum, group.groupNum) &&
                equalsItems(group.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, show_as, groupNum, items);
    }

    private Boolean equalsItems(Set<Item> items) {
        boolean equals = true;

        for (Item item : this.items) {
            if (!items.contains(item)) {
                equals = false;
            }
        }

        return equals;
    }
}

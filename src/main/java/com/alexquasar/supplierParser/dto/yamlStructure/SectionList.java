package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;
import java.util.Set;

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
    private Set<Group> groups;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionList that = (SectionList) o;
        return Objects.equals(listId, that.listId) &&
                Objects.equals(listType, that.listType) &&
                equalsGroups(that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listId, listType, groups);
    }

    private Boolean equalsGroups(Set<Group> groups) {
        boolean equals = true;

        for (Group group : this.groups) {
            if (!groups.contains(group)) {
                equals = false;
            }
        }

        return equals;
    }
}

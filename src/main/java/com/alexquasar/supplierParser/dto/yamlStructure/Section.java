package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;
import java.util.Set;

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
    private Set<SectionList> lists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(section_id, section.section_id) &&
                Objects.equals(integrityFixed, section.integrityFixed) &&
                Objects.equals(section_type, section.section_type) &&
                equalsSectionLists(section.lists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section_id, integrityFixed, section_type, lists);
    }

    private Boolean equalsSectionLists(Set<SectionList> lists) {
        boolean equals = true;

        for (SectionList sectionList : this.lists) {
            if (!lists.contains(sectionList)) {
                equals = false;
            }
        }

        return equals;
    }
}

package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;
import java.util.Set;

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
    private Set<Section> sections;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(dataId, data.dataId) &&
                Objects.equals(integrityFixed, data.integrityFixed) &&
                equalsSections(data.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataId, integrityFixed, sections);
    }

    private Boolean equalsSections(Set<Section> sections) {
        boolean equals = true;

        for (Section section : this.sections) {
            if (!sections.contains(section)) {
                equals = false;
            }
        }

        return equals && this.sections.size() == sections.size();
    }
}

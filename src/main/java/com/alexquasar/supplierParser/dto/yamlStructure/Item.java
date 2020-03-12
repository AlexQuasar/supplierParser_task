package com.alexquasar.supplierParser.dto.yamlStructure;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"date"})
public class Item {

    @JsonProperty("item-id")
    private String itemId;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("source")
    private String source;

    @JsonProperty("date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @JsonProperty("label")
    private String label;

    @JsonProperty("type")
    private String type;

    @JsonProperty("doc_proc_context")
    private String doc_proc_context;

    @JsonProperty("context")
    private String context;

    @JsonProperty("transition")
    private String transition;

    @JsonProperty("value")
    private String value;
}

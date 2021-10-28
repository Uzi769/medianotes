package com.golovin.hospital.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.golovin.hospital.enums.MedicalsFields;
import lombok.Getter;

import java.util.Collection;

@Getter
public class MedicalFilterDto {

    private final MedicalsFields medicalsFields;

    private final Collection<String> values;

    public MedicalFilterDto(@JsonProperty("medicalsFields") MedicalsFields medicalsFields,
                            @JsonProperty("values") Collection<String> values) {
        this.medicalsFields = medicalsFields;
        this.values = values;
    }
}

package domain.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.entity.Pet;
import domain.entity.Type;
import lombok.Data;

@Data
public class TypeOutputDto {
    @JsonProperty("type_name")
    private final String name;
    @JsonProperty("pet")
    private final Pet pet;

    public TypeOutputDto(final Type type) {
        this.name = type.getName();
        this.pet = type.getPet();
    }
}

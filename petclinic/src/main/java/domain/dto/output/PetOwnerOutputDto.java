package domain.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PetOwnerOutputDto {
    @JsonProperty("pet_birth_date")
    private final Date birthDate;
    @JsonProperty("pet_id")
    private final Long id;
    @JsonProperty("pet_name")
    private final String name;
    @JsonProperty("type")
    private final TypeOwnerOutputDto type;
}

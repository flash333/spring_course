package domain.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TypeOwnerOutputDto {
    @JsonProperty("type_id")
    private final Long id;
    @JsonProperty("type_name")
    private final String name;
}

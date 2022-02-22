package domain.dto.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@EqualsAndHashCode
@Getter
public class TypeInputDto {

    @NotBlank
    private final String name;

    @JsonCreator
    public TypeInputDto(String name) {
        this.name = name;
    }
}

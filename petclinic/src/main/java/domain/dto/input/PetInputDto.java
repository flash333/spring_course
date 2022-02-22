package domain.dto.input;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Value
public class PetInputDto {

    @NotBlank
    private final Date birth_date;

    @NotBlank
    private final String name;

    public final Long ownerId;

    @Valid
    public final String typeName;
}

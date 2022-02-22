package domain.dto.output;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NameOwnerOutputDto {

    @NotBlank
    private final String owner_first_name;

    @NotBlank
    private final String owner_last_name;
}

package domain.dto.output.superDto;

import lombok.Data;

@Data
public class NameOutputDto {

    private final String first_name;
    private final String last_name;

    public NameOutputDto(final String first_name, final String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
}

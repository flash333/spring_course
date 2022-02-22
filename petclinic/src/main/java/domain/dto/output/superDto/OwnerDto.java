package domain.dto.output.superDto;

import domain.entity.Owner;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OwnerDto {
    @NotBlank
    private final String address;

    @NotBlank
    private final Long id;

    @NotBlank
    private final NameOutputDto name;

    @NotBlank
    private final String telephone;

    public OwnerDto(final Owner owner) {
        this.address = owner.getAddress();
        this.id = owner.getId();
        this.name = new NameOutputDto(owner.getFirst_name(), owner.getLast_name());
        this.telephone = owner.getTelephone();
    }
}

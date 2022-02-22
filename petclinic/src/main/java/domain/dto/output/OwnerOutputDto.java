package domain.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.entity.Owner;
import lombok.Data;

import java.util.List;

@Data
public class OwnerOutputDto {
    @JsonProperty("owner_id")
    private final Long ownerId;
    @JsonProperty("owner_address")
    private final String ownerAddress;
    @JsonProperty("owner_name")
    private final NameOwnerOutputDto ownerName;
    @JsonProperty("pets")
    private final List<PetOutputDto> pets;
    @JsonProperty("owner_telephone")
    private final String ownerTelephone;

    public OwnerOutputDto(final Owner owner) {
        this.ownerId = owner.getId();
        this.ownerAddress = owner.getAddress();
        this.ownerName = new NameOwnerOutputDto(owner.getFirst_name(), owner.getLast_name());
        this.pets = owner.getPets().stream().map(PetOutputDto::new).toList();
        this.ownerTelephone = owner.getTelephone();
    }
}

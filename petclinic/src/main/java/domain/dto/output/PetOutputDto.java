package domain.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.entity.Pet;
import lombok.Data;

import java.util.Date;

@Data
public class PetOutputDto {
    @JsonProperty("pet_birth_date")
    private final Date petBirthDate;
    @JsonProperty("pet_id")
    private final Long petId;
    @JsonProperty("pet_name")
    private final String petName;
    @JsonProperty("owner")
    private final OwnerOutputDto petOwner;
    @JsonProperty("type")
    private final TypeOutputDto petType;

    public PetOutputDto(final Pet pet) {
        this.petBirthDate = pet.getBirth_date();
        this.petId = pet.getId();
        this.petName = pet.getName();
        this.petOwner = new OwnerOutputDto(pet.getOwner());
        this.petType = new TypeOutputDto(pet.getType());
    }
}

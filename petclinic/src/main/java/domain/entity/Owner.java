package domain.entity;

import domain.dto.input.OwnerInputDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
@Setter
@Getter
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "owner_id")
    private Long id;

    @Column(name = "owner_address", nullable = false)
    private String address;

    @Column(name = "owner_first_name", nullable = false)
    private String first_name;

    @Column(name = "owner_last_name", nullable = false)
    private String last_name;

    @Column(name = "owner_telephone", nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Pet> pets = new ArrayList<>();

    public Owner(final OwnerInputDto ownerInput) {
        this.id = ownerInput.getId();
        this.address = ownerInput.getAddress();
        this.first_name = ownerInput.getFirst_name();
        this.last_name = ownerInput.getLast_name();
        this.telephone = ownerInput.getTelephone();
    }

    public void addPet(final Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }

    public void removePet(final Pet pet) {
        pets.remove(pet);
        pet.setOwner(null);
    }
}

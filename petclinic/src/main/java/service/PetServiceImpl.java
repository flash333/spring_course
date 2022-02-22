package service;

import dal.PetRepository;
import domain.dto.input.PetInputDto;
import domain.entity.Owner;
import domain.entity.Pet;
import domain.entity.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.api.OwnerService;
import service.api.TypeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements service.api.PetService {
    private final PetRepository petRepository;
    private final OwnerService ownerService;
    private final TypeService typeService;

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s id pet not found", id)));
    }

    @Override
    public Pet save(PetInputDto inputDto) {
        final Owner owner = ownerService.findById(inputDto.getOwnerId());
        final Pet pet = new Pet();
        final Type type = new Type();
        type.setName(inputDto.getTypeName());
        pet.setOwner(owner);
        pet.setType(type);
        pet.setBirth_date(inputDto.getBirth_date());
        pet.setName(inputDto.getName());
        return petRepository.save(pet);
    }
}

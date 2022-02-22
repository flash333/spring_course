package service.api;

import domain.dto.input.PetInputDto;
import domain.entity.Pet;

import java.util.List;

public interface PetService {
    List<Pet> findAll();

    Pet findById(Long id);

    Pet save(PetInputDto inputDto);
}

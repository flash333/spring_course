package service.api;

import domain.dto.input.OwnerInputDto;
import domain.entity.Owner;

import java.util.List;

public interface OwnerService {
    Owner findById(Long id);

    List<Owner> findAll();

    Owner save(OwnerInputDto ownerInput);
}

package service;

import dal.OwnerRepository;
import domain.dto.input.OwnerInputDto;
import domain.entity.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.api.OwnerService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public Owner findById(final Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s id owner not found", id)));
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner save(final OwnerInputDto ownerInput) {
        return ownerRepository.save(new Owner(ownerInput));
    }

}

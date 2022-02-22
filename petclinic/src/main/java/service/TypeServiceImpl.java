package service;

import dal.TypeRepository;
import domain.dto.input.TypeInputDto;
import domain.entity.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements service.api.TypeService {
    private final TypeRepository typeRepository;

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s id type not found", id)));
    }

    @Override
    public Type save(TypeInputDto inputDto) {
        final Type type = new Type();
        type.setName(inputDto.getName());
        return typeRepository.save(type);
    }
}

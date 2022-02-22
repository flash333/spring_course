package service.api;

import domain.dto.input.TypeInputDto;
import domain.entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAll();

    Type findById(Long id);

    Type save(TypeInputDto inputDto);
}

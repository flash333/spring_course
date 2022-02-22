package controller;

import controller.api.base.FetchAllController;
import domain.dto.input.TypeInputDto;
import domain.dto.output.TypeOutputDto;
import domain.entity.Type;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.api.TypeService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@Api(tags = "Type Controller")
@RequestMapping("/type")
public class TypeControllerImpl implements FetchAllController<TypeInputDto, TypeOutputDto> {

    @Autowired
    private TypeService typeService;

    @Override
    public ResponseEntity saveEntity(TypeInputDto input) {
        final Type savedType = typeService.save(input);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedType.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @ApiResponse(code = SC_OK, message = "Type fetched", response = TypeOutputDto.class)
    public ResponseEntity<TypeOutputDto> fetchEntityById(@PathVariable("type_id") final Long id) {
        final Type type = typeService.findById(id);
        return ResponseEntity.ok(new TypeOutputDto(type));
    }

    @Override
    @ApiResponse(code = SC_OK, message = "Types fetched", responseContainer = "List", response = TypeOutputDto.class)
    public ResponseEntity<List<TypeOutputDto>> fetchAllEntities() {
        final List<Type> types = typeService.findAll();
        final List<TypeOutputDto> typeOutputDtos = types.stream().map(TypeOutputDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(typeOutputDtos);
    }
}

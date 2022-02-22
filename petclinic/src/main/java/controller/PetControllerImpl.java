package controller;

import controller.api.base.BaseController;
import domain.dto.input.PetInputDto;
import domain.dto.output.PetOutputDto;
import domain.entity.Pet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.api.PetService;

import java.net.URI;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@Api(tags = "Pet Controller")
@RequestMapping("/pet")
public class PetControllerImpl implements BaseController<PetInputDto, PetOutputDto> {
    @Autowired
    private PetService petService;

    @Override
    public ResponseEntity saveEntity(PetInputDto input) {
        final Pet savedPet = petService.save(input);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPet.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @ApiResponse(code = SC_OK, message = "Pet fetched", response = PetOutputDto.class)
    public ResponseEntity<PetOutputDto> fetchEntityById(@PathVariable("pet_id") final Long id) {
        final Pet pet = petService.findById(id);
        return ResponseEntity.ok(new PetOutputDto(pet));
    }
}

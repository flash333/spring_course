package controller;

import controller.api.base.FetchAllController;
import domain.dto.input.OwnerInputDto;
import domain.dto.output.OwnerOutputDto;
import domain.dto.output.exception.ExceptionOutputDto;
import domain.entity.Owner;
import domain.entity.Pet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.api.OwnerService;
import service.api.PetService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@Api(tags = "Owner Controller")
@RequestMapping("/owner")
public class OwnerControllerImpl implements FetchAllController<OwnerInputDto, OwnerOutputDto> {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PetService petService;

    @Override
    public ResponseEntity saveEntity(@RequestBody final OwnerInputDto input) {
        final Owner savedOwner = ownerService.save(input);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOwner.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @ApiOperation("Return Entities")
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Owner fetched", response = OwnerOutputDto.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error", response = ExceptionOutputDto.class)
    })
    public ResponseEntity<OwnerOutputDto> fetchEntityById(@PathVariable("owner_id") final Long id) {
        final Owner owner = ownerService.findById(id);
        final List<Pet> pets = petService.findAll().stream().filter(pet -> pet.getOwner().getId().equals(id)).toList();
        owner.setPets(pets);
        return ResponseEntity.ok(new OwnerOutputDto(owner));
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Return Entities")
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Owners fetched", responseContainer = "List", response = OwnerOutputDto.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error", response = ExceptionOutputDto.class)
    })
    public ResponseEntity<List<OwnerOutputDto>> fetchAllEntities() {
        final List<Owner> owners = ownerService.findAll();
        final List<OwnerOutputDto> ownerOutputDtos = owners.stream().map(OwnerOutputDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(ownerOutputDtos);
    }
}

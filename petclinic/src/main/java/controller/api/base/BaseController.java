package controller.api.base;

import domain.dto.output.exception.ExceptionOutputDto;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static javax.servlet.http.HttpServletResponse.*;

public interface BaseController<IN, OUT> {

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = SC_CREATED, message = "Entity saved"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error", response = ExceptionOutputDto.class)
    })
    ResponseEntity saveEntity(@RequestBody IN input);

    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Entity fetched", responseContainer = "List"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Requested Owner not found", response = ExceptionOutputDto.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error", response = ExceptionOutputDto.class)
    })
    ResponseEntity<OUT> fetchEntityById(Long id);
}

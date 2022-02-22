package domain.dto.output.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@JsonPropertyOrder({"statusCode", "error", "invalidFields"})
public final class ExceptionOutputDto extends RuntimeException {
    @JsonProperty("error")
    private final String error;

    @JsonProperty("invalidFields")
    private final List<InvalidFieldOutputDto> invalidFields;

    @JsonProperty("statusCode")
    private final Integer statusCode;
}

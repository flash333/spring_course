package domain.dto.output.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonPropertyOrder({"errorMessage", "fieldName"})
public class InvalidFieldOutputDto extends RuntimeException {
    @JsonProperty("errorMessage")
    private final String errorMessage;
    @JsonProperty("fieldName")
    private final String fieldName;
}

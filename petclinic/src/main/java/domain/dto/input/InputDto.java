package domain.dto.input;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class InputDto<T> {
    @NotNull
    @Positive
    protected Long id;

    @JsonCreator
    public InputDto(final Long id) {
        this.id = id;
    }
}

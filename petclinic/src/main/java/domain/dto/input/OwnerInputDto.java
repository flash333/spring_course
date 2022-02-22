package domain.dto.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@ToString
@EqualsAndHashCode
@Getter
public class OwnerInputDto {

    @NotNull
    @Positive
    private final Long id;

    @NotBlank
    private final String address;

    @NotBlank
    private final String first_name;

    @NotBlank
    private final String last_name;

    @NotBlank
    @Pattern(regexp = "((?:\\+?3|0)6)[-(]?(\\d{1,2})[-)]?(\\d{3})-?(\\d{3,4})")
    private final String telephone;

    @JsonCreator
    public OwnerInputDto(@JsonProperty("owner_id") Long id,
                         @JsonProperty("owner_address") String address,
                         @JsonProperty("owner_first_name") String first_name,
                         @JsonProperty("owner_last_name") String last_name,
                         @JsonProperty("owner_telephone") String telephone) {
        this.id = id;
        this.address = address;
        this.first_name = first_name;
        this.last_name = last_name;
        this.telephone = telephone;
    }
}

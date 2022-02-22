import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.dto.input.OwnerInputDto;
import domain.dto.input.PetInputDto;
import domain.dto.input.TypeInputDto;
import domain.entity.Pet;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ApplicationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomPort;

    @Test
    @ResetDatabase
    @SneakyThrows
    void shouldGetOwnerById() {
        final OwnerInputDto ownerInput1 = new OwnerInputDto(1L, "Budapest", "Joseph", "Barbera", "12345");
        final HttpEntity<OwnerInputDto> httpEntity = new HttpEntity<>(ownerInput1);
        final ResponseEntity<Void> saveEntity = testRestTemplate.postForEntity("/owner", httpEntity, Void.class);

        final URI saveEntityLocation = saveEntity.getHeaders().getLocation();

        final ResponseEntity<OwnerOutputTestDto> responseEntity = testRestTemplate.getForEntity(saveEntityLocation, OwnerOutputTestDto.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        final OwnerOutputTestDto ownerOutput = Objects.requireNonNull(responseEntity.getBody());
//        Assertions.assertThat(ownerOutput.getOwnerId()).isEqualTo(1L);
        Assertions.assertThat(ownerOutput.getOwnerAddress()).isEqualTo("Budapest");
        Assertions.assertThat(ownerOutput.getOwnerName().getFirstName()).isEqualTo("Joseph");
        Assertions.assertThat(ownerOutput.getOwnerName().getLastName()).isEqualTo("Barbera");
        Assertions.assertThat(ownerOutput.getOwnerTelephone()).isEqualTo("12345");
//        Assertions.assertThat(ownerOutput.getPets()).isEmpty();
    }

    @Test
    @ResetDatabase
    @SneakyThrows
    void shouldGetOwnerByIdWithPets() {
        final OwnerInputDto ownerInput1 = new OwnerInputDto(2L, "Budapest", "William", "Hanna", "12345");
        final TypeInputDto cat = new TypeInputDto("Cat");
        final PetInputDto tomInputDto = new PetInputDto(new Date(), "Tom", ownerInput1.getId(), cat.getName());

        final HttpEntity<OwnerInputDto> httpEntity = new HttpEntity<>(ownerInput1);

        final ResponseEntity<Void> saveEntity = testRestTemplate.postForEntity("/owner", httpEntity, Void.class);

        final URI saveEntityLocation = saveEntity.getHeaders().getLocation();

        final ResponseEntity<OwnerOutputTestDto> responseEntity = testRestTemplate.getForEntity(saveEntityLocation, OwnerOutputTestDto.class);


        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        final OwnerOutputTestDto ownerOutput = Objects.requireNonNull(responseEntity.getBody());
        Assertions.assertThat(ownerOutput.getOwnerAddress()).isEqualTo("Budapest");
        Assertions.assertThat(ownerOutput.getOwnerName().getFirstName()).isNotEqualTo("Joseph");
        Assertions.assertThat(ownerOutput.getOwnerName().getLastName()).isNotEqualTo("Barbera");
        Assertions.assertThat(ownerOutput.getOwnerTelephone()).isEqualTo("12345");
//        Assertions.assertThat(ownerOutput.getPets()).isEmpty();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Sql(scripts = "classpath:db/reset_integration.sql")
    public @interface ResetDatabase {
    }

    @Data
    public static class OwnerOutputTestDto {
        private final String ownerAddress;
        private final Long ownerId;
        private final NameOutputTestDto ownerName;
        private final List<PetOutputTestDto> pets;
        private final String ownerTelephone;

        @JsonCreator
        public OwnerOutputTestDto(@JsonProperty("owner_id") final Long ownerId,
                                  @JsonProperty("owner_address") final String ownerAddress,
                                  @JsonProperty("owner_name") final NameOutputTestDto ownerName,
                                  @JsonProperty("pets") final List<PetOutputTestDto> pets,
                                  @JsonProperty("owner_telephone") final String ownerTelephone) {
            this.ownerAddress = ownerAddress;
            this.ownerId = ownerId;
            this.ownerName = ownerName;
            this.pets = pets;
            this.ownerTelephone = ownerTelephone;
        }
    }

    @Data
    public static class PetOutputTestDto {
        private final Date petBirthDate;
        private final Long petId;
        private final String petName;
        private final OwnerOutputTestDto petOwner;
        private final TypeOutputTestDto petType;

        @JsonCreator
        public PetOutputTestDto(@JsonProperty("pet_birth_date") final Date petBirthDate,
                                @JsonProperty("pet_id") final Long petId,
                                @JsonProperty("pet_name") final String petName,
                                @JsonProperty("owner") final OwnerOutputTestDto petOwner,
                                @JsonProperty("type") final TypeOutputTestDto petType) {
            this.petBirthDate = petBirthDate;
            this.petId = petId;
            this.petName = petName;
            this.petOwner = petOwner;
            this.petType = petType;
        }
    }

    @Data
    public static class TypeOutputTestDto {
        private final String name;
        private final Pet pet;

        @JsonCreator
        public TypeOutputTestDto(@JsonProperty("type_name") final String name,
                                 @JsonProperty("pet") final Pet pet) {
            this.name = name;
            this.pet = pet;
        }
    }

    @Data
    public static class NameOutputTestDto {
        private final String firstName;
        private final String lastName;

        @JsonCreator
        public NameOutputTestDto(@JsonProperty("owner_first_name") final String firstName,
                                 @JsonProperty("owner_last_name") final String lastName) {

            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
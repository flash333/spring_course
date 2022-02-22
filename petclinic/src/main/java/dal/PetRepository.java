package dal;

import domain.entity.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("petRepository")
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Override
    @EntityGraph("fetch-post")
    List<Pet> findAll();

    @Override
    @EntityGraph("fetch-post")
    Optional<Pet> findById(Long aLong);
}

package dal;

import domain.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ownerRepository")
public interface OwnerRepository extends JpaRepository<Owner, Long> {
//    @Transactional
//    @Query("  select pets "
//            + "from City as city "
//            + "where city.coordinate.latitude >= :latitudeStart "
//            + "  and city.coordinate.latitude <= :latitudeEnd"
//            + "  and city.coordinate.longitude >= :longitudeStart "
//            + "  and city.coordinate.longitude <= :longitudeEnd")
//    List<Pet> fetchOwnerPets(@Param("id") final Long id);
}

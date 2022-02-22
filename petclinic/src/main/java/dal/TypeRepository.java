package dal;

import domain.entity.Type;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("typeRepository")
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Override
    @EntityGraph(attributePaths = "type")
    List<Type> findAll();
}

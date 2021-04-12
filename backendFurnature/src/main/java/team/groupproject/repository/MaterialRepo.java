package team.groupproject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.groupproject.entity.Material;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Integer> {

    Material findById(int id);

    Optional<Material> findByName(String name);

}

package team.groupproject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.groupproject.entity.Style;

public interface StyleRepo extends JpaRepository<Style, Integer> {

    Optional<Style> findByName(String name);

}

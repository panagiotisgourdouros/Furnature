package team.groupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.groupproject.entity.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer> {

    public Status findByName(String name);

}

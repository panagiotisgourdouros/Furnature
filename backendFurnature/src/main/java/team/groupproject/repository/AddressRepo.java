package team.groupproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.groupproject.entity.Address;
import team.groupproject.entity.Myuser;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

    public Address findById(int id);

    @Query("SELECT a FROM Address  a WHERE a.myuser_id= ?1")
    public List<Address> findByUserId(Myuser user);

}

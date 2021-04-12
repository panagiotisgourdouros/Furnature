package team.groupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.groupproject.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

    public Cart findById(int id);

}

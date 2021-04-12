package team.groupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.groupproject.entity.OrderDetails;
import team.groupproject.entity.OrderDetailsPK;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, OrderDetailsPK> {
}

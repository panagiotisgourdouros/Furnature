package team.groupproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import team.groupproject.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findByMyUserId(int id);

}

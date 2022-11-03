package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.FoodItem;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
	public List<FoodItem> findByStallId(Long stallId);
}

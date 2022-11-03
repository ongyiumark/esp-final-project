package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.FoodStall;

@Repository
public interface FoodStallRepository extends JpaRepository<FoodStall, Long>{
	public FoodStall findByStallName(String stallName);
	public List<FoodStall> findByLocationId(Long locationId);
}

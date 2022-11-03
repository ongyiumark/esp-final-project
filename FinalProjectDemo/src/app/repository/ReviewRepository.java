package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	public List<Review> findByStallId(Long stallId);
}

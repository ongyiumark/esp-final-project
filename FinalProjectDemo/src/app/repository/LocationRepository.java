package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
	public Location findByLocationName(String locationName);
}

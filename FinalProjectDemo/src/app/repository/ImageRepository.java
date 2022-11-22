package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	public Image findByFileName(String fileName);
}

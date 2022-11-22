package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
	public Session findBySessionKey(String sessionKey);
}

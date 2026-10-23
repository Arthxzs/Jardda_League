package league.jardda.backend.repository;

import league.jardda.backend.entity.Clube;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
}

package pl.agntyp.zadanie25;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDurationRepository extends JpaRepository<TaskDuration, Long> {
}

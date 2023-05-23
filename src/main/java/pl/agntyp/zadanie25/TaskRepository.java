package pl.agntyp.zadanie25;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDoneIsFalse();

    List<Task> findByDoneIsTrue();

    List<Task> findByDoneIsFalseOrderByDueDateDesc();

    List<Task> findByDoneIsTrueOrderByDueDateDesc();

    List<Task> findByDoneIsFalseOrderByDueDateAsc();

    List<Task> findByDoneIsTrueOrderByDueDateAsc();

    List<Task> findByCategory(Category category);
}

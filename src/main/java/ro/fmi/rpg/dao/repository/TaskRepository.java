package ro.fmi.rpg.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.fmi.rpg.dao.entity.Task;

import java.util.List;

/**
 * Created by User on 12/11/2016.
 */
public interface TaskRepository  extends JpaRepository<Task, Integer> {

    @Query(" SELECT t FROM Task t " +
            "LEFT JOIN FETCH t.category c " +
            "INNER JOIN c.user u " +
            "WHERE u.id = :userId")
    List<Task> findUserTasks(@Param("userId") int userId);
}

package ro.fmi.rpg.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.fmi.rpg.dao.entity.UserEvent;

import java.util.List;

/**
 * Created by User on 2/7/2017.
 */
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

    @Query("SELECT e from UserEvent e " +
            "WHERE e.user.id IN :userIds")
    List<UserEvent> getUserEvents(@Param("userIds") List<Integer> userIds);

}

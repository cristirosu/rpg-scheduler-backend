package ro.fmi.rpg.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.fmi.rpg.dao.entity.Achievement;
import ro.fmi.rpg.to.task.AchievementModel;

import java.util.List;

/**
 * Created by User on 12/20/2016.
 */
public interface AcvhievementRepository extends JpaRepository<Achievement, String> {

    @Query("SELECT a FROM User u " +
            "INNER JOIN u.achievements a " +
            "WHERE u.id = :userId AND a.name = :name")
    Achievement getAcvhievementByName(@Param("name") String name, @Param("userId") int id);

    @Query("SELECT new ro.fmi.rpg.to.task.AchievementModel(a.name, a.description, a.picture) FROM User u " +
            "INNER JOIN u.achievements a " +
            "WHERE u.id = :userId")
    List<AchievementModel> getAchievementsForUser(@Param("userId") int id);

}

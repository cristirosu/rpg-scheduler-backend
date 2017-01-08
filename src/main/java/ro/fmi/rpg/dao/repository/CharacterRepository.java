package ro.fmi.rpg.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.fmi.rpg.dao.entity.Character;
import ro.fmi.rpg.dao.entity.User;

/**
 * Created by User on 12/18/2016.
 */
public interface CharacterRepository extends JpaRepository<Character, String> {

    @Query("SELECT c FROM Character c " +
            "WHERE c.user.id = :userId")
    Character getChar(@Param("userId") int userId);

}

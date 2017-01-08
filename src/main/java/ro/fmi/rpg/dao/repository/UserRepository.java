package ro.fmi.rpg.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.fmi.rpg.dao.entity.User;

/**
 * Created by User on 11/13/2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u " +
            " WHERE u.email = :email AND u.password = :password")
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM User u " +
            " WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

}

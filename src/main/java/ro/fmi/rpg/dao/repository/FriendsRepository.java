package ro.fmi.rpg.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.fmi.rpg.dao.entity.UserFriend;
import ro.fmi.rpg.to.FriendModel;

import java.util.List;

/**
 * Created by User on 1/8/2017.
 */
public interface FriendsRepository extends JpaRepository<UserFriend, Integer>{

    @Query(" SELECT new ro.fmi.rpg.to.FriendModel(f.id, f.firstName, f.lastName, c.level, c.experience, c.health, false, c.picture) " +
           " FROM UserFriend uf " +
           " INNER JOIN uf.friend f " +
           " INNER JOIN f.character c " +
           " WHERE uf.user.id = :userId OR uf.friend.id = :userId")
    public List<FriendModel> getFriendsByUser(@Param("userId") Integer userId);

    @Query(" SELECT uf FROM UserFriend uf " +
           " WHERE uf.friend.id = :friendId AND uf.user.id = :userId")
    public UserFriend findByFriendId(@Param("userId")Integer userId, @Param("friendId") Integer friendId);
}
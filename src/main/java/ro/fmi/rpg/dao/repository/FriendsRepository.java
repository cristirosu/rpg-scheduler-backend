package ro.fmi.rpg.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.fmi.rpg.dao.entity.UserFriend;
import ro.fmi.rpg.to.FriendModel;
import ro.fmi.rpg.to.LeaderBoardModel;

import java.util.List;

/**
 * Created by User on 1/8/2017.
 */
public interface FriendsRepository extends JpaRepository<UserFriend, Integer>{

    @Query(" SELECT new ro.fmi.rpg.to.FriendModel(f.id, f.firstName, f.lastName, c.level, c.experience, c.health, false, c.picture) " +
            " FROM UserFriend uf " +
            " INNER JOIN uf.friend f " +
            " INNER JOIN f.character c " +
            " WHERE uf.user.id = :userId")
    List<FriendModel> getFriendsByUser(@Param("userId") Integer userId);

    @Query(" SELECT uf FROM UserFriend uf " +
            " WHERE (uf.friend.id = :friendId AND uf.user.id = :userId) OR (uf.friend.id = :userId AND uf.user.id = :friendId)")
    List<UserFriend> findByFriendId(@Param("userId")Integer userId, @Param("friendId") Integer friendId);

    @Query(" SELECT uf FROM UserFriend uf " +
            " WHERE uf.friend.email = :email AND uf.user.id = :userId")
    UserFriend findFriendByEmail(@Param("userId") Integer userId, @Param("email") String email);

    @Query( " SELECT f.id FROM UserFriend uf " +
            " INNER JOIN uf.friend f " +
            " WHERE uf.user.id = :userId AND f.receivesToasts = '1'")
    List<Integer> getFriendIdsByUser(@Param("userId") Integer id);

    @Query( " SELECT new ro.fmi.rpg.to.LeaderBoardModel(f.firstName, f.lastName, c.picture, c.level, f.email) FROM UserFriend uf " +
            " INNER JOIN uf.friend f " +
            " INNER JOIN f.character c " +
            " WHERE uf.user.id = :userId")
    List<LeaderBoardModel> getScoreBoard(@Param("userId") Integer id);
}
package fareye;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by himanshu on 10/11/15.
 */

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,String>{

    User findByEmail(@Param("email") String email);
    User findByUsername(@Param("username") Long username);
    //Required at the time of login
    User findByEmailOrPhone(@Param("email") String email, @Param("phone") String phone);
    //Required at the time of signup
    User findByEmailAndPhone(@Param("email") String email, @Param("phone") String phone);

    @Modifying
    @Query(value="select u from User u")
    List<User> findAllUsers();

    @Modifying
    @Transactional
    @Query(value = "update User u set u.password=?1 where u.email=?2")
    //@Query(value = "update User u set u.password=:newPassword where u.email=:email")
    void resetUserPassword(String newPassword, String email);

    //Get list of people whom current user follows.
    @Query("select u.name,u.username,u.profileImgUrl from User u where u.username IN (select f.followsTo from Follower f where f.username=?1 AND NOT (f.username=?1 AND f.followsTo=?1))")
    List<UserTemplate> whomIFollow(long myUsername);

    //Get list of recommendations whom the current user can follow.
    @Query("select u.name,u.username,u.profileImgUrl from User u where u.username NOT IN (select f.followsTo from Follower f where f.username=?1) And u.username <> ?1")
    List<UserTemplate> recommendation(long myUsername);

}

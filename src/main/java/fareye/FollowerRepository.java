package fareye;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by himanshu on 18/11/15.
 * To update user follow details.
 */
@Repository
public interface FollowerRepository extends JpaRepository<Follower,String> {

    Follower findByUsername(@Param("username") long username);

    //Used when we need to un-follow a user.
    @Modifying
    @Transactional
    @Query(value="delete from Follower f where f.username=?1 AND f.followsTo=?2")
    void deleteFollowerRecord(long username, long followsTo);
}

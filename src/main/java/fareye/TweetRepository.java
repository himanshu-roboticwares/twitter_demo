package fareye;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* Created by himanshu on 13/11/15.
 * Handles tweet operations
*/
@Repository
public interface TweetRepository extends JpaRepository<Tweet,Integer> {

    //Get my tweets only
    @Query(value="select t from Tweet t where t.username=?1 order by t.timeSt desc")
    List getMyTweets(long username);

    //Get tweets from me and the user to whom I follow.
    @Query(value="select u.username, u.name, t.timeSt, t.message, u.profileImgUrl from User u, Tweet t where t.username IN (select f.followsTo from Follower f where f.username=?1) AND u.username=t.username Order by t.timeSt desc")
    List getCurrentTweets(long username);
}
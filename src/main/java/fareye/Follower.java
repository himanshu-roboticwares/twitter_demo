package fareye;

import javax.persistence.*;

/**
 * Created by himanshu on 18/11/15.
 */

@Entity
@Table(name="follower")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long username;
    @Column(name = "followsto")
    private long followsTo;

//    constructors
    public Follower(){

    }

    public Follower(long username, long followsTo){
        this.username=username;
        this.followsTo=followsTo;
    }

//    getters and setters
    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }

    public long getFollowsTo() {
        return followsTo;
    }

    public void setFollowsTo(long followsTo) {
        this.followsTo = followsTo;
    }
}

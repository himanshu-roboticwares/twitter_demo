package fareye;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
* Created by himanshu on 13/11/15
 * Example of jpa database handling for a table with composite primary-key.
*/

@Entity
@Table(name="tweets")
//@IdClass used for scenarios where composite keys are used
@IdClass(TweetPrimaryKey.class)
public class Tweet {

    //Two keys forming primary key of 'tweets' table
    @Id private long username;
    @Id
    @Column(name="timest")
    private Timestamp timeSt;

    private String message;

//    constructors
    public Tweet(){

    }

    public Tweet(long username, String message){
        this.username=username;
        java.util.Date date= new java.util.Date();
        this.timeSt=new Timestamp(date.getTime());
        this.message=message;
    }

//    getters and setters
    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }

    public Timestamp getTimeSt() {
        return timeSt;
    }

    public void setTimeSt() {
        //set current timestamp
        java.util.Date date= new java.util.Date();
        this.timeSt=new Timestamp(date.getTime());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

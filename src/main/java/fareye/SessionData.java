package fareye;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by himanshu on 16/11/15.
 * To store session data: used by spring boot's java classes only
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData implements Serializable{

    private long username;

//    Constructors
    public SessionData(){

    }

    public SessionData(long username){
        this.username=username;
    }

//    Getters and setters
    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }

}

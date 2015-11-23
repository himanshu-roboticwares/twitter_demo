package fareye;

/**
 * Created by himanshu on 18/11/15.
 */
public class UserTemplate {
    private String name;
    private String username;
    private String profileImgUrl;

    //constructor
    public UserTemplate(){

    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}

package fareye;

/**
 * Created by himanshu on 18/11/15.
 * Template for the search result rendered by tweet repository query.
 */
public class TweetTemplate {

    private String username;
    private String name;
    private String timest;
    private String message;
    private String profileImgUrl;

    //constructor
    public TweetTemplate(){

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

    public String getTimest() {
        return timest;
    }

    public void setTimest(String timest) {
        this.timest = timest;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}

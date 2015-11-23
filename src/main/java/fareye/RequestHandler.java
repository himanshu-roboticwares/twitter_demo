package fareye;

import com.sun.deploy.net.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by himanshu on 6/11/15.
 */

@RestController
public class RequestHandler {

    //Repository injection for user management operations.
    @Autowired
    UserRepository userRepository;

    //Repository injection for tweet management operations
    @Autowired
    TweetRepository tweetRepository;

    //Repository injection for follow management operations
    @Autowired
    FollowerRepository followerRepository;

    //Session Data injection
    @Autowired
    SessionData sessionData;

    //Listener for adding new user
    @RequestMapping("/addUser")
    public ResponseEntity handleAddUserRequest(@RequestBody User user) {
        try {
            //Deny user account if email or phone is already registered.
            if (userRepository.findByEmailOrPhone(user.getEmail(),user.getPhone()) == null){
                //By default, account status is inactive.
                user.setAcStatus("Inactive");
                //Assign default image to user profile.
                user.setProfileImgUrl("../profileImgs/default_user.jpg");
                userRepository.save(user);

                //Allow user follow himself
                //Update user instance to get user's auto generated username
                user=userRepository.findByEmail(user.getEmail());
                followerRepository.save(new Follower(user.getUsername(),user.getUsername()));

                //Store data on session
                sessionData.setUsername(user.getUsername());

                return new ResponseEntity(user,HttpStatus.OK);// send user data to Angularjs
            } else {// If email already registered then send status found.
                return new ResponseEntity(null,HttpStatus.FOUND);
            }
        } catch (Exception e) {
            System.out.println("Exception in handleAddUserRequest. " + e);
        }
        return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
    }

    //Listener for authenticating login credential request
    @RequestMapping("/auth")
    public ResponseEntity handleAuthenticationRequest(@RequestBody String json) {
        try {
            JSONObject login_cred = new JSONObject(json);
            User user = userRepository.findByEmailOrPhone(login_cred.getString("username"), login_cred.getString("username"));
            if (user != null) {
                if (login_cred.getString("password").equals(user.getPassword())) {
                    //Store data on session
                    sessionData.setUsername(user.getUsername());
                    return new ResponseEntity(user, HttpStatus.OK);// send user data to Angularjs
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    //Listener for change password request.
    @RequestMapping("/changePswd")
    public ResponseEntity handleChangePasswordRequest(@RequestBody String json) {
        try {
            JSONObject resetPswd = new JSONObject(json);
            User user = userRepository.findByUsername(sessionData.getUsername());  //get credentials of logged-in user
            if (user.getPassword().equals(resetPswd.getString("oldPass")))       //if password matches...
            {
                userRepository.resetUserPassword(resetPswd.getString("newPassword"), resetPswd.getString("email"));
                return new ResponseEntity(user,HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(null,HttpStatus.CONFLICT);    //User credentials are wrong. So, unauthorized to change password.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
    }

    //Listener for uploading profile image.
    @RequestMapping(value="/uploadImage",headers=("content-type=multipart/*"),method= RequestMethod.POST)
    public void handleProfileImageUploadRequest(@RequestBody MultipartFile file, HttpServletResponse resp){
       if (!file.isEmpty()) {
            try {
                //Save file at server
                User user=userRepository.findByUsername(sessionData.getUsername());
                byte[] bytes = file.getBytes();
                String name=user.getEmail()+".jpg";
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/webapp/profileImgs/"+name)));
                stream.write(bytes);
                stream.close();
                //Update user profileImgUrl field in database.
                user.setProfileImgUrl("../profileImgs/"+name);
                userRepository.save(user);
                //Redirect to profile page
                resp.sendRedirect("/#/dashboard/profile");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("file is null");
        }
    }

    //Listener for profile info
    @RequestMapping(value="/profileData")
    public ResponseEntity handleGetProfileDataRequest(){
        //Get details logged-in of user.
        User user=userRepository.findByUsername(sessionData.getUsername());
        return new ResponseEntity(user,HttpStatus.OK);
    }

    //Listener for uploading tweets
    @RequestMapping(value = "/uploadNewTweet", method = RequestMethod.POST)
    public HttpStatus handleUploadTweetRequest(@RequestBody Tweet newTweet) {
        //Add username and current timestamp to tweet instance and save in table.
        newTweet.setUsername(sessionData.getUsername());
        newTweet.setTimeSt();
        tweetRepository.save(newTweet);
        return HttpStatus.OK;
    }

    //Listener to find my Tweets
    @RequestMapping("/getMyTweets")
    public ResponseEntity handleShowMyTweetRequest() {
        List<Tweet> tweetList = tweetRepository.getMyTweets(sessionData.getUsername());
        JSONArray respArr = new JSONArray();
        try {
            for (Tweet t : tweetList) {
                JSONObject dummy = new JSONObject();
                dummy.put("time", t.getTimeSt());
                dummy.put("message", t.getMessage());
                respArr.put(dummy);
            }
            return new ResponseEntity(tweetList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    //Listener to list out users to whom I follow
    @RequestMapping("/whomIFollow")
    public ResponseEntity handleWhomIFollowRequest() {
        List<UserTemplate> iFollow = userRepository.whomIFollow(sessionData.getUsername());
        return new ResponseEntity(iFollow, HttpStatus.OK);
    }

    //Listener to send recommendations
    @RequestMapping("/recommendations")
    public ResponseEntity handleGetRecommendationRequest() {
        List<UserTemplate> recommendToFollow = userRepository.recommendation(sessionData.getUsername());
        return new ResponseEntity(recommendToFollow, HttpStatus.OK);
    }

    //Listener for block the user request
    @RequestMapping("/blockUser")
    public HttpStatus handleBlockUserRequest(@RequestBody Follower block){
        block.setUsername(sessionData.getUsername());
        followerRepository.deleteFollowerRecord(block.getUsername(), block.getFollowsTo());
        return HttpStatus.OK;
    }

    //Listener for 'follow' request
    @RequestMapping("/addToFollows")
    public HttpStatus handleFollowRequest(@RequestBody Follower follower) {
        follower.setUsername(sessionData.getUsername());
        followerRepository.save(follower);
        return HttpStatus.OK;
    }

    //Listener for get all tweets request
    @RequestMapping("/showCurrentTweets")
    public ResponseEntity handleGetCurrentTweetRequest(){
        List<TweetTemplate> listTweet=tweetRepository.getCurrentTweets(sessionData.getUsername());
        return new ResponseEntity(listTweet,HttpStatus.OK);
    }

}
package fareye;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by himanshu on 10/11/15.
 */

@Entity
@Table(name="users")
public class User {
    //Primary key of relation
    @Id
    private String email;

    @Generated(GenerationTime.ALWAYS)
    @Column(name="username", insertable=false,updatable=false)
    private long username;

    @Column(updatable = false)
    private String name;
    @Column(updatable=true)
    private String password;
    private String phone;
    @Column(name="profileimgurl")
    private String profileImgUrl;
    @Column(name="acstatus")
    private String acStatus;

    //Constructor
    public User(){

    }

    public User(String email, String name, String password){
       // this.username=Long.parseLong(username);
        this.email=email;
        this.password=password;
        this.name=name;
        this.profileImgUrl="..//profileImgs//default_user.jpg"; //Assign default image to every user.
        this.acStatus="Inactive";   //Will be active post Email verification.
    }

    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(String acStatus) {
        this.acStatus = acStatus;
    }

    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
       this.username = username;
    }
}

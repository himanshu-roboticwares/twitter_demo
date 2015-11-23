/**
 * Created by himanshu on 16/11/15.
 */
app.service('loggedUserDetails',[ '$localStorage',function( $localStorage){

    this.setName=function(uname){
        $localStorage.name = uname;
    }
    this.getName=function(){
        return $localStorage.name;
    }

    this.setUrl=function(iUrl){
        $localStorage.url = iUrl;
    }

    this.getUrl=function(){
        return $localStorage.url;
    }

    this.setUsername=function(userName){
        $localStorage.username = userName;
    }

    this.getUsername=function(){
        return $localStorage.username;
    }
}]);
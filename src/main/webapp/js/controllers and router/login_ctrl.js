/**
 * Created by himanshu on 9/11/15.
 */
app.controller('login_ctrl', ['$window','$interval','$scope','$http','$state','loggedUserDetails' ,function($window, $interval,$scope, $http, $state, loggedUserDetails){

//    Initialize JSON
    $scope.login_cred={};
    $scope.new_user={};

//    Background Image handling with $interval
    var last=0;
    var img=['img/tweet.jpg','img/tweet1.jpg','img/tweet1.jpg'];

    $interval(function(){
        last=(last+1)%img.length
        $scope.back_img=img[last];
    },5000);

//    Add new user
    $scope.addUser=function(){
        $http({
            url:'/addUser',
            method:'POST',
            data:$scope.new_user
        }).then(
            function successCallback(response){
                if(response.statusText=="OK")
                {
                    //If signup successful, then store data in service.
                    loggedUserDetails.setName(response.data.name);
                    loggedUserDetails.setUsername(response.data.username);
                    loggedUserDetails.setUrl(response.data.profileImgUrl);
                    //take him to his Dashboard.
                    $state.go('Dashboard.default');
                }
            },
            function errorCallback(response){
                if(response.statusText=="FOUND") {//If email or contact number is already registered
                    $window.alert("Email ID or Contact Number already registered!");
                }
                else{//Otherwise
                    $window.alert("Failed to create new user!");
                }
                //clear form fields
                $scope.new_user = "";
            }
        );
    }


//    Authenticate Login Credentials
    $scope.authUser=function(){
        var req={
            url:'/auth',
            method:'POST',
            data:$scope.login_cred
        };
        $http.post("/auth", $scope.login_cred).then(function (response) {
            if(response.statusText=="OK"){
                //If login success, save data in service.
                loggedUserDetails.setName(response.data.name);
                loggedUserDetails.setUsername(response.data.username);
                loggedUserDetails.setUrl(response.data.profileImgUrl);
                //Take him to his Dashboard.
                $state.go('Dashboard.default');
            }
        }, function (response) {
            // show error at  login div.
            $scope.invalid_cred=true;
            //Clear form fields.
            $scope.login_cred="";
        });
    }

}])
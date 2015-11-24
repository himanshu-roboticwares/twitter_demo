/**
 * Created by himanshu on 13/11/15.
 */
app.controller('dashboard_ctrl', ['$scope','$modal','loggedUserDetails','$state','$localStorage','$timeout', function($scope,$modal,loggedUserDetails,$state, $localStorage, $timeout){
    if(loggedUserDetails.getUsername()!=null){  //i.e. User session exists.
        $scope.name=loggedUserDetails.getName();
        $scope.welcome_links=['My_Profile','Change_Password','Follow','My_Tweets'];
        $scope.ImageUrl=loggedUserDetails.getUrl();
        $state.go('Dashboard.default');
        //  To open tweet modal box
        $scope.popTweetWindow=function(){
            var tweetInstance=$modal.open({
                templateUrl:'templates/modal.html',
                controller: 'modal_ctrl'
            });
        }
    }
    else
    {
        $state.go('Login');
    }
    $scope.logout=function(){
        loggedUserDetails.setName(null);
        loggedUserDetails.setUsername(null);
        loggedUserDetails.setUrl(null);
        $state.go('Login');
    }

    //Handle profile image url change
    $scope.$watch('$localStorage.url', function(){
        //changes are not immediately reflected. So, wait for 5 seconds.
        $timeout(function(){
            console.log("$watch in dash "+$localStorage.url);
            console.log(loggedUserDetails.getUrl());
            $scope.ImageUrl=loggedUserDetails.getUrl();
        },5000);
    });

//    //Listener for call made at Update profile Image url
//    $rootScope.$on('updateImgUrl', function(){
//        $scope.ImageUrl=loggedUserDetails.getUrl();
//    });
}]);
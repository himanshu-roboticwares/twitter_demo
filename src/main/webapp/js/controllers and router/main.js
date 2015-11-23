/**
 * Created by himanshu on 6/11/15.
 */

var app = angular.module("twitter_app",['ui.router','ui.bootstrap','ngStorage']);

app.controller("main_ctrl", ['$window','$scope','$state','loggedUserDetails',function($window, $scope, $state, loggedUserDetails){

//    Footer link menu items
    $scope.footerLinks=["About","Help","Blog","Status","Jobs","Terms","Privacy","Cookies","Adds","Info","Media","Business","Developer","Directory","@ 2015 Twitter"];

//    Logged-in user menu items
    $scope.userMenuTabs=["My Profile", "Change Password", "Recent Tweets", "Follow Tweets", "My Tweets", "Logout"];

//    By default open login state
    $scope.checkState=function(){
        if(loggedUserDetails.getUsername()!=null)
            $state.go('Dashboard.default');
        else
            $state.go('Login');
    }();
}]);
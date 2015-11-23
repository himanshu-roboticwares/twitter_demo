/**
 * Created by himanshu on 9/11/15.
 */
app.config(function($stateProvider){

    $stateProvider

        .state('Login',{
            url:'/login',
            templateUrl:'templates/login.html',
            controller:'login_ctrl'
        })
        .state('Dashboard',{
            url:'/dashboard',
            templateUrl: 'templates/dashboard.html',
            controller: 'dashboard_ctrl'
        })
        .state('Dashboard.default',{
            url:'/dashboard_welcome',
            templateUrl: 'templates/dashboard_default.html',
            controller: 'showAllTweets_ctrl'
        })
        .state('Dashboard.Change_Password',{
            url:'/change_user_password',
            templateUrl:'templates/changePassword.html',
            controller: 'changePswd_ctrl'
        })
        .state('Dashboard.My_Profile',{
            url:'/profile',
            templateUrl:'templates/profile.html',
            controller:'profile_ctrl'
        })
        .state('Dashboard.My_Tweets',{
            url:'/myTweets',
            templateUrl:'templates/myTweets.html',
            controller:'myTweet_ctrl'

        })
        .state('Dashboard.Follow',{
            url:'/recommendations',
            templateUrl:'templates/recommendation.html',
            controller:'recommend_ctrl'

        })
});
/**
 * Created by himanshu on 18/11/15.
 */
app.controller('showAllTweets_ctrl',['$scope','$http','$interval','loggedUserDetails','$localStorage','$timeout',function($scope,$http,$interval,loggedUserDetails, $localStorage, $timeout){

    $scope.ImageUrl=loggedUserDetails.getUrl();
    $scope.Name=loggedUserDetails.getName();
    $scope.Username=loggedUserDetails.getUsername();

    //call for the first time
    $http({
        url:'/showCurrentTweets',
        method: 'GET',
        data:{}
    }).then(
        function(response){
            console.log(response);
            if(response.data.length==0){
                $scope.no_tweets=true;
            }
            else{
                $scope.no_tweets=false;
                $scope.tweets=response.data;
            }
        }
    );

    //Repeated calls after 1 minute to server to get latest tweet
    $interval(function(){$http({
        url:'/showCurrentTweets',
        method: 'GET',
        data:{}
    }).then(
        function(response){
            console.log(response);
            if(response.data.length==0){
                $scope.no_tweets=true;
            }
            else{
                $scope.no_tweets=false;
                $scope.tweets=response.data;
            }
        }
    );},60000);


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
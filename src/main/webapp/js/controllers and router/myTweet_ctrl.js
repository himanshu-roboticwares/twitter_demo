/**
 * Created by himanshu on 17/11/15.
 */
app.controller('myTweet_ctrl',[ '$scope','$http','$rootScope','loggedUserDetails',function($scope,$http,$rootScope,loggedUserDetails){

    $scope.imageUrl=loggedUserDetails.getUrl();
    $scope.userdata=loggedUserDetails.getUsername();
    $scope.author=loggedUserDetails.getName();
        var req={
            url:'/getMyTweets',
            method: 'GET',
            data:   {}
        };
        $http(req).then(
            function(response){
                if(response.data.length==0){
                    $scope,no_MyTweets=true;
                }
                else {
                    $scope.tweets = response.data;
                    $scope.author = loggedUserDetails.getName();
                    $scope.no_MyTweets=false;
                }
            },
            function (response) {
                $scope.tweets=null;
                $scope,no_MyTweets=true;
            }
        );

    //Handle new tweets added by me.
    $rootScope.$on('myNewTweet', function(){
        $http(req).then(
            function(response){
                $scope.tweets=response.data;
                $scope.author=loggedUserDetails.getName();
                $scope,no_MyTweets=false;
            },
            function (response) {
                $scope.tweets=null;
                $scope,no_MyTweets=true;
            }
        );
    })

}]);
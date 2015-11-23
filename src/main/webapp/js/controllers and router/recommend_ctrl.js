/**
* Created by himanshu on 17/11/15.
*/

//follows is an array of user objects where each object contains
/*
 Index   Attribute
 0       name
 1       username
 2       profile image url
 */

app.controller('recommend_ctrl', ['$http','$scope', '$window', function($http,$scope,$window){
   $scope.follows={};
   $scope.recommendations={};

    //To show user and recommendation lists.
    $http.get("/whomIFollow").then(
       function(response){
           console.log(response.data);
           $scope.follows=response.data;
       },
       function(response){
           $window.alert("Failed to get data from service");
       }
    );

    $http.get("/recommendations").then(
        function(response){
            console.log(response.data);
            $scope.recommendations=response.data;
        },
        function(response){
            $window.alert("Failed to get data from service");
        }
    );

    //To un-follow a user.
    $scope.block=function(username,index){
        $http({
            url:'/blockUser',
            method:'POST',
            data:{followsTo:username}
        }).then(
            function(response){
                var json=$scope.follows[index];
                console.log(json);
                $scope.follows.splice(index,1);
                $scope.recommendations.push(json);
            },
            function(response){

            }
        )
    }

    //To follow a user.
    $scope.follow=function(username,index){
        $http({
            url: '/addToFollows',
            method: 'POST',
            data: {followsTo:   username}
        }).then(
            function successCallback(response){
                var json=$scope.recommendations[index];
                console.log(json);
                $scope.recommendations.splice(index,1);
                $scope.follows.push(json);

            },
            function errorCallback(response){
                $window.alert("Failed to process the request!");
            }
        );
     }
}]);
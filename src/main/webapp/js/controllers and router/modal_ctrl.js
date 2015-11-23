/**
 * Created by himanshu on 18/11/15.
 */

app.controller('modal_ctrl',['$scope','$http','$modalInstance','$rootScope',function($scope,$http,$modalInstance, $rootScope){

    //  To post tweet in database
    $scope.tweetdata={};
    $scope.postNewTweet=function(){
        $http({
            url:  '/uploadNewTweet',
            method: 'POST',
            data: $scope.tweetdata
        }).then(
            function successCallBack(response){
                //Refleect new tweet at My_Tweet page
                $rootScope.$broadcast('myNewTweet');
                $scope.discard();
            },
            function errorCallBack(response){
                $window.alert("Failed to post the tweet.");
                $scope.discard();
            }
        )
    }

    //To close modal box
    $scope.discard = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
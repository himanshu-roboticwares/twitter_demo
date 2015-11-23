/**
 * Created by himanshu on 17/11/15.
 */

app.controller('changePswd_ctrl',['$scope','$http','$window','$state', function($scope, $http, $window,$state){
    //Initialize json
    $scope.reset = {};

    $scope.changePswd = function () {
        $http({
            url: '/changePswd',
            method: 'POST',
            data: $scope.reset
        }).then(
            function successCallback(response) {
                console.log(response);
                if (response.data = "ACCEPTED") {
                    $state.go('Dashboard.default');
                }
            },
            function errorCallback(response) {
                if (response.data = "CONFLICT") {
                    $window.alert("Unauthorized to change password of requested account.");
                    $state.go('Dashboard.default');
                }
                else {
                    console.log("Failed to change user password");
                }
            }
        );
    }
}]);

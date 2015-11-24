/**
 * Created by himanshu on 16/11/15.
 */

//                  References: https://code.google.com/p/angular-file-upload/
//                              https://spring.io/guides/gs/uploading-files/

app.controller('profile_ctrl',['$http','$scope','$window','$modal','$rootScope','$localStorage','loggedUserDetails',function($http, $scope, $window, $modal,$rootScope, $localStorage,loggedUserDetails){
    $scope.profile={};
    $http({
        url:'/profileData',
        method:'GET',
        data:{}
    }).then(
        function (response) {
            console.log(response.data);
            $scope.profile=response.data;
            //update new profile image path in service
            loggedUserDetails.setUrl($scope.profile.profileImgUrl);
            //Update at Dashboard and Dashboard default.
//            $rootScope.$broadcast('updateImgUrl');
        },
        function (response) {
            $window.alert("Failed to get data from server");
        }
    );

    $scope.uploadImage=function(){
        var modalInsance=$modal.open({
            templateUrl:    'templates/uploadProfileImage.html',
            controller:     'upload_ctrl'
        });
   }
}]);
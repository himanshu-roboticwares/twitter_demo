/**
 * Created by himanshu on 19/11/15.
 */
app.controller('upload_ctrl',['$scope','$modalInstance',function($scope,$modalInstance){

    //To close modal box
    $scope.discard = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
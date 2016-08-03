'use strict';

angular.module('nScannerApp').controller('DeviceDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Device', 'TestScript',
        function($scope, $stateParams, $modalInstance, entity, Device, TestScript) {

        $scope.device = entity;
        $scope.load = function(id) {
            Device.get({id : id}, function(result) {
                $scope.device = result;
            });
        };
        Device.query(function(result) {
        	$scope.devices = result;
        });
        TestScript.query(function(result){
        	$scope.testScripts = result;
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('nScannerApp:deviceUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            console.log($scope.device);
            if ($scope.device.id != null) {
                Device.update($scope.device, onSaveSuccess, onSaveError);
            } else {
                Device.save($scope.device, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);

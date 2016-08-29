'use strict';

angular.module('nScannerApp').controller('DeviceDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Device', 'TestScript', 'NetworkInterface', '$filter',
        function($scope, $stateParams, $modalInstance, entity, Device, TestScript, NetworkInterface, $filter) {

        $scope.device = entity;
        $scope.load = function(id) {
            Device.get({id : id}, function(result) {
                $scope.device = result;
                if($scope.device.devices == undefined){
            		$scope.device.devices = [];
            	}
                if($scope.device.testScripts == undefined){
            		$scope.device.testScripts = [];
            	}
                if($scope.device.networkInterfaces == undefined){
                	$scope.device.networkInterfaces = [];
                }
            });
        };
        Device.query(function(result) {
        	$scope.devices = result;
        });
        TestScript.query(function(result){
        	$scope.testScripts = result;
        });
        NetworkInterface.query(function(result){
        	$scope.networkInterfaces = result;
        });
        
        $scope.$watch('device.expirationDate', function (newValue) {
            $scope.device.expirationDate = $filter('date')(newValue, 'dd-MM-yyyy HH:mm:ss'); 
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

'use strict';

angular.module('nScannerApp').controller('NetworkServiceDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'NetworkService', 'TestScript', 'Device',
        function($scope, $stateParams, $modalInstance, entity, NetworkService, TestScript, Device) {

        $scope.networkService = entity;
        $scope.load = function(id) {
            NetworkService.get({id : id}, function(result) {
                $scope.networkService = result;
            });
        };
        TestScript.query(function(result){
        	$scope.testScripts = result;
        });
        Device.query(function(result){
        	$scope.devices = result;
        });


        var onSaveSuccess = function (result) {
            $scope.$emit('nScannerApp:networkServiceUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.networkService.id != null) {
                NetworkService.update($scope.networkService, onSaveSuccess, onSaveError);
            } else {
                NetworkService.save($scope.networkService, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);

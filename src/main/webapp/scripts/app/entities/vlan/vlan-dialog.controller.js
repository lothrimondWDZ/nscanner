'use strict';

angular.module('nScannerApp').controller('VLANDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'VLAN', 'TestScript', 'Network', 'NetworkInterface',
        function($scope, $stateParams, $modalInstance, entity, VLAN, TestScript, Network, NetworkInterface) {

        $scope.vlan = entity;
        $scope.load = function(id) {
            VLAN.get({id : id}, function(result) {
                $scope.vlan = result;
            });
        };
        VLAN.query(function(result) {
        	$scope.vlans = result;
        });
        TestScript.query(function(result){
        	$scope.testScripts = result;
        });
        Network.query(function(result){
        	$scope.networks = result;
        });
        NetworkInterface.query(function(result){
        	$scope.networkInterfaces = result;
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('nScannerApp:vlanUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.vlan.id != null) {
                VLAN.update($scope.vlan, onSaveSuccess, onSaveError);
            } else {
                VLAN.save($scope.vlan, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);

'use strict';

angular.module('nScannerApp').controller('NetworkInterfaceDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'NetworkInterface',
        function($scope, $stateParams, $modalInstance, entity, NetworkInterface) {

        $scope.networkInterface = entity;
        $scope.load = function(id) {
            NetworkInterface.get({id : id}, function(result) {
                $scope.networkInterface = result;
            });
        };

        $scope.addIPadd = function() {
        	if($scope.networkInterface.addresses == undefined){
        		$scope.networkInterface.addresses = [];
        	}
        	$scope.networkInterface.addresses.push({id: null, address: null, type: null});
        };
        
        $scope.removeIPadd = function(index) {
        	$scope.networkInterface.addresses.splice(index, 1);
        }
        
        var onSaveSuccess = function (result) {
            $scope.$emit('nScannerApp:networkInterfaceUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.networkInterface.id != null) {
                NetworkInterface.update($scope.networkInterface, onSaveSuccess, onSaveError);
            } else {
                NetworkInterface.save($scope.networkInterface, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);

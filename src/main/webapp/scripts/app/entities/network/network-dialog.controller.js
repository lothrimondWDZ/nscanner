'use strict';

angular.module('nScannerApp').controller('NetworkDialogController',
    ['$scope', '$stateParams', '$modalInstance', '$q', 'entity', 'Network', 'IPaddress', 'TestScript',
        function($scope, $stateParams, $modalInstance, $q, entity, Network, IPaddress, TestScript) {

        $scope.network = entity;
        $scope.networkaddresss = IPaddress.query({filter: 'network-is-null'});
        $q.all([$scope.network.$promise, $scope.networkaddresss.$promise]).then(function() {
            if (!$scope.network.networkAddress || !$scope.network.networkAddress.id) {
                return $q.reject();
            }
            return IPaddress.get({id : $scope.network.networkAddress.id}).$promise;
        }).then(function(networkAddress) {
            $scope.networkaddresss.push(networkAddress);
        });
        $scope.load = function(id) {
            Network.get({id : id}, function(result) {
                $scope.network = result;
            });
        };
        TestScript.query(function(result){
        	$scope.testScripts = result;
        });
        var onSaveSuccess = function (result) {
            $scope.$emit('nScannerApp:networkUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.network.id != null) {
                Network.update($scope.network, onSaveSuccess, onSaveError);
            } else {
                Network.save($scope.network, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);

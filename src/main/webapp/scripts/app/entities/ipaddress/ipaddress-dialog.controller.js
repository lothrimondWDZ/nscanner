'use strict';

angular.module('nScannerApp').controller('IPaddressDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'IPaddress',
        function($scope, $stateParams, $modalInstance, entity, IPaddress) {

        $scope.ipaddress = entity;
        $scope.load = function(id) {
            IPaddress.get({id : id}, function(result) {
                $scope.ipaddress = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('nScannerApp:ipaddressUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.ipaddress.id != null) {
                IPaddress.update($scope.ipaddress, onSaveSuccess, onSaveError);
            } else {
                IPaddress.save($scope.ipaddress, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);

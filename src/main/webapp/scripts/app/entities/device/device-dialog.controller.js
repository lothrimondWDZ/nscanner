'use strict';

angular.module('nScannerApp').controller('DeviceDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Device',
        function($scope, $stateParams, $modalInstance, entity, Device) {

        $scope.device = entity;
        $scope.load = function(id) {
            Device.get({id : id}, function(result) {
                $scope.device = result;
            });
        };

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

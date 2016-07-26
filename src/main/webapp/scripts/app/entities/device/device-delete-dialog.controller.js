'use strict';

angular.module('nScannerApp')
	.controller('DeviceDeleteController', function($scope, $modalInstance, entity, Device) {

        $scope.device = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Device.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });
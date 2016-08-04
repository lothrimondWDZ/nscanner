'use strict';

angular.module('nScannerApp')
	.controller('NetworkInterfaceDeleteController', function($scope, $modalInstance, entity, NetworkInterface) {

        $scope.networkInterface = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            NetworkInterface.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });
'use strict';

angular.module('nScannerApp')
	.controller('VLANDeleteController', function($scope, $modalInstance, entity, VLAN) {

        $scope.vlan = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            VLAN.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });
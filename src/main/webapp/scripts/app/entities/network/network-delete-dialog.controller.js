'use strict';

angular.module('nScannerApp')
	.controller('NetworkDeleteController', function($scope, $modalInstance, entity, Network) {

        $scope.network = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Network.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });
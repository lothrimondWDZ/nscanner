'use strict';

angular.module('nScannerApp')
	.controller('NetworkServiceDeleteController', function($scope, $modalInstance, entity, NetworkService) {

        $scope.networkService = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            NetworkService.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });
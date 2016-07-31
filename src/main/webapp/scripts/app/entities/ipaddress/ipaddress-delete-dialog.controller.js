'use strict';

angular.module('nScannerApp')
	.controller('IPaddressDeleteController', function($scope, $modalInstance, entity, IPaddress) {

        $scope.ipaddress = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            IPaddress.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });
'use strict';

angular.module('nScannerApp')
	.controller('TestScriptDeleteController', function($scope, $modalInstance, entity, TestScript) {

        $scope.testScript = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            TestScript.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });
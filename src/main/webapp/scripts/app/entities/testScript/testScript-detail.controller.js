'use strict';

angular.module('nScannerApp')
    .controller('TestScriptDetailController', function ($scope, $rootScope, $stateParams, entity, TestScript) {
        $scope.testScript = entity;
        $scope.load = function (id) {
            TestScript.get({id: id}, function(result) {
                $scope.testScript = result;
            });
        };
        var unsubscribe = $rootScope.$on('nScannerApp:testScriptUpdate', function(event, result) {
            $scope.testScript = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

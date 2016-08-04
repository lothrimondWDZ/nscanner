'use strict';

angular.module('nScannerApp')
    .controller('NetworkInterfaceDetailController', function ($scope, $rootScope, $stateParams, entity, NetworkInterface) {
        $scope.networkInterface = entity;
        $scope.load = function (id) {
            NetworkInterface.get({id: id}, function(result) {
                $scope.networkInterface = result;
            });
        };
        var unsubscribe = $rootScope.$on('nScannerApp:networkInterfaceUpdate', function(event, result) {
            $scope.networkInterface = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

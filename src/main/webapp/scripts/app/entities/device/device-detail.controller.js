'use strict';

angular.module('nScannerApp')
    .controller('DeviceDetailController', function ($scope, $rootScope, $stateParams, entity, Device) {
        $scope.device = entity;
        $scope.load = function (id) {
            Device.get({id: id}, function(result) {
                $scope.device = result;
            });
        };
        var unsubscribe = $rootScope.$on('nScannerApp:deviceUpdate', function(event, result) {
            $scope.device = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

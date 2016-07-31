'use strict';

angular.module('nScannerApp')
    .controller('NetworkDetailController', function ($scope, $rootScope, $stateParams, entity, Network, IPaddress) {
        $scope.network = entity;
        $scope.load = function (id) {
            Network.get({id: id}, function(result) {
                $scope.network = result;
            });
        };
        var unsubscribe = $rootScope.$on('nScannerApp:networkUpdate', function(event, result) {
            $scope.network = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

'use strict';

angular.module('nScannerApp')
    .controller('IPaddressDetailController', function ($scope, $rootScope, $stateParams, entity, IPaddress) {
        $scope.ipaddress = entity;
        $scope.load = function (id) {
            IPaddress.get({id: id}, function(result) {
                $scope.ipaddress = result;
            });
        };
        var unsubscribe = $rootScope.$on('nScannerApp:ipaddressUpdate', function(event, result) {
            $scope.ipaddress = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

'use strict';

angular.module('nScannerApp')
    .controller('VLANDetailController', function ($scope, $rootScope, $stateParams, entity, VLAN) {
        $scope.vlan = entity;
        $scope.load = function (id) {
            VLAN.get({id: id}, function(result) {
                $scope.vlan = result;
            });
        };
        var unsubscribe = $rootScope.$on('nScannerApp:vlanUpdate', function(event, result) {
            $scope.vlan = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

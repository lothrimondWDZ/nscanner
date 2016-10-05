'use strict';

angular.module('nScannerApp')
    .controller('NetworkInterfaceDetailController', function ($scope, $rootScope, $stateParams, entity, NetworkInterface) {
        $scope.networkInterface = entity;
        $scope.searchFilter = "";
        $scope.load = function (id) {
            NetworkInterface.get({id: id}, function(result) {
                $scope.networkInterface = result;
            });
        };
        NetworkInterface.query(function(result) {
        	$scope.interfaces = result;
        });
        $scope.connectTo = function(intId) {
        	NetworkInterface.connect({first: $scope.networkInterface.id, second: intId});
        };
        var unsubscribe = $rootScope.$on('nScannerApp:networkInterfaceUpdate', function(event, result) {
            $scope.networkInterface = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

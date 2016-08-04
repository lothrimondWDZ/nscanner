'use strict';

angular.module('nScannerApp')
    .controller('NetworkServiceDetailController', function ($scope, $rootScope, $stateParams, entity, NetworkService) {
        $scope.networkService = entity;
        $scope.load = function (id) {
            NetworkService.get({id: id}, function(result) {
                $scope.networkService = result;
            });
        };
        var unsubscribe = $rootScope.$on('nScannerApp:networkServiceUpdate', function(event, result) {
            $scope.networkService = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

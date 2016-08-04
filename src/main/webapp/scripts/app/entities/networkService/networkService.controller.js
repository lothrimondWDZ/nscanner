'use strict';

angular.module('nScannerApp')
    .controller('NetworkServiceController', function ($scope, $state, $modal, NetworkService, ParseLinks) {
      
        $scope.networkServices = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            NetworkService.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.networkServices = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.networkService = {
                name: null,
                description: null,
                port: null,
                id: null
            };
        };
    });

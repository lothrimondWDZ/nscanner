'use strict';

angular.module('nScannerApp')
    .controller('NetworkController', function ($scope, $state, $modal, Network, ParseLinks) {
      
        $scope.networks = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Network.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.networks = result;
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
            $scope.network = {
                name: null,
                mask: null,
                id: null
            };
        };
    });

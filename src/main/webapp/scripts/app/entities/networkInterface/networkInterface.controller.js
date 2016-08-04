'use strict';

angular.module('nScannerApp')
    .controller('NetworkInterfaceController', function ($scope, $state, $modal, NetworkInterface, ParseLinks) {
      
        $scope.networkInterfaces = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            NetworkInterface.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.networkInterfaces = result;
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
            $scope.networkInterface = {
                name: null,
                description: null,
                type: null,
                id: null
            };
        };
    });

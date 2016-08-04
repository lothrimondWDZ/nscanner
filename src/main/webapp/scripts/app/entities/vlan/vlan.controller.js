'use strict';

angular.module('nScannerApp')
    .controller('VLANController', function ($scope, $state, $modal, VLAN, ParseLinks) {
      
        $scope.vlans = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            VLAN.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.vlans = result;
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
            $scope.vlan = {
                number: null,
                type: null,
                id: null
            };
        };
    });

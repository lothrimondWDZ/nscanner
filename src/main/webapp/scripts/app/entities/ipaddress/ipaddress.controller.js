'use strict';

angular.module('nScannerApp')
    .controller('IPaddressController', function ($scope, $state, $modal, IPaddress) {
      
        $scope.ipaddresss = [];
        $scope.loadAll = function() {
            IPaddress.query(function(result) {
               $scope.ipaddresss = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.ipaddress = {
                address: null,
                type: null,
                id: null
            };
        };
    });

'use strict';

angular.module('nScannerApp')
    .controller('DeviceController', function ($scope, $state, $modal, Device, ParseLinks) {
      
        $scope.devices = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Device.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.devices = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.compareDate = function(date){
        	if(date) {
        		return (new Date(date)).getTime() < (new Date()).getTime();
        	}
        	return false;
        };
        
        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.device = {
                name: null,
                description: null,
                id: null
            };
        };
    });

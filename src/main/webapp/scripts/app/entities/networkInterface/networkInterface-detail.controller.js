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
        	NetworkInterface.get({id: intId}, function(result) {
        		$scope.networkInterface.vlans = $scope.arrayUnique($scope.networkInterface.vlans.concat(result.vlans));
        		NetworkInterface.update($scope.networkInterface);
            });
        };
        
        $scope.arrayUnique = function (array) {
            var a = array.concat();
            for(var i=0; i<a.length; ++i) {
                for(var j=i+1; j<a.length; ++j) {
                    if(a[i] === a[j])
                        a.splice(j--, 1);
                }
            }

            return a;
        }
        
        var unsubscribe = $rootScope.$on('nScannerApp:networkInterfaceUpdate', function(event, result) {
            $scope.networkInterface = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

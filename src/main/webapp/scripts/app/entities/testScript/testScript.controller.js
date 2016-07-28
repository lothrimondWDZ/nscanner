'use strict';

angular.module('nScannerApp')
    .controller('TestScriptController', function ($scope, $state, $modal, TestScript, ParseLinks) {
      
        $scope.testScripts = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            TestScript.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.testScripts = result;
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
            $scope.testScript = {
                name: null,
                description: null,
                cronExpression: null,
                path: null,
                id: null
            };
        };
    });

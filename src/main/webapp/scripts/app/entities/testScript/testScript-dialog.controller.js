'use strict';

angular.module('nScannerApp').controller('TestScriptDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'TestScript',
        function($scope, $stateParams, $modalInstance, entity, TestScript) {

        $scope.testScript = entity;
        $scope.load = function(id) {
            TestScript.get({id : id}, function(result) {
                $scope.testScript = result;
            });
        };
        
        $scope.addParam = function() {
        	if($scope.testScript.parameters == undefined){
        		$scope.testScript.parameters = [];
        	}
        	$scope.testScript.parameters.push({id: null, value: null});
        };
        
        $scope.removeParam = function(index) {
        	$scope.testScript.parameters.splice(index, 1);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('nScannerApp:testScriptUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.testScript.id != null) {
                TestScript.update($scope.testScript, onSaveSuccess, onSaveError);
            } else {
                TestScript.save($scope.testScript, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);

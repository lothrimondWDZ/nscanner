 'use strict';

angular.module('nScannerApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-nScannerApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-nScannerApp-params')});
                }
                return response;
            }
        };
    });

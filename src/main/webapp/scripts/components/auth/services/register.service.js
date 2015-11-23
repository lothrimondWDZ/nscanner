'use strict';

angular.module('nScannerApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });



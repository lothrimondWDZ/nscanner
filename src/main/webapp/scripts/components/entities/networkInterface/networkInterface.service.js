'use strict';

angular.module('nScannerApp')
    .factory('NetworkInterface', function ($resource, DateUtils) {
        return $resource('api/networkInterfaces/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

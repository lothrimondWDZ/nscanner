'use strict';

angular.module('nScannerApp')
    .factory('TestScript', function ($resource, DateUtils) {
        return $resource('api/testScripts/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' },
            'run': { 
            	method: 'POST',
            	params: {id: '@id'},
            	url: 'api/testScripts/run/:id/'
            }
        });
    });

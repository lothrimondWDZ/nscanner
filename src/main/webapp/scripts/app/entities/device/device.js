'use strict';

angular.module('nScannerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('device', {
                parent: 'entity',
                url: '/devices',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.device.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/device/devices.html',
                        controller: 'DeviceController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('device');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('device.detail', {
                parent: 'entity',
                url: '/device/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.device.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/device/device-detail.html',
                        controller: 'DeviceDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('device');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Device', function($stateParams, Device) {
                        return Device.get({id : $stateParams.id});
                    }]
                }
            })
            .state('device.new', {
                parent: 'device',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/device/device-dialog.html',
                        controller: 'DeviceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    description: null,
                                    expirationDate: null,
                                    id: null,
                                    devices: [],
                                    testScripts: []
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('device', null, { reload: true });
                    }, function() {
                        $state.go('device');
                    })
                }]
            })
            .state('device.edit', {
                parent: 'device',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/device/device-dialog.html',
                        controller: 'DeviceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Device', function(Device) {
                                return Device.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('device', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('device.delete', {
                parent: 'device',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/device/device-delete-dialog.html',
                        controller: 'DeviceDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Device', function(Device) {
                                return Device.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('device', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

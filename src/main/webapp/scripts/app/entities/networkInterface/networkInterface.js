'use strict';

angular.module('nScannerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('networkInterface', {
                parent: 'entity',
                url: '/networkInterfaces',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.networkInterface.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/networkInterface/networkInterfaces.html',
                        controller: 'NetworkInterfaceController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('networkInterface');
                        $translatePartialLoader.addPart('networkInterfaceType');
                        $translatePartialLoader.addPart('ipaddress');
                        $translatePartialLoader.addPart('iPAddressType');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('networkInterface.detail', {
                parent: 'entity',
                url: '/networkInterface/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.networkInterface.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/networkInterface/networkInterface-detail.html',
                        controller: 'NetworkInterfaceDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('networkInterface');
                        $translatePartialLoader.addPart('networkInterfaceType');
                        $translatePartialLoader.addPart('ipaddress');
                        $translatePartialLoader.addPart('iPAddressType');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'NetworkInterface', function($stateParams, NetworkInterface) {
                        return NetworkInterface.get({id : $stateParams.id});
                    }]
                }
            })
            .state('networkInterface.new', {
                parent: 'networkInterface',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/networkInterface/networkInterface-dialog.html',
                        controller: 'NetworkInterfaceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    description: null,
                                    type: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('networkInterface', null, { reload: true });
                    }, function() {
                        $state.go('networkInterface');
                    })
                }]
            })
            .state('networkInterface.edit', {
                parent: 'networkInterface',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/networkInterface/networkInterface-dialog.html',
                        controller: 'NetworkInterfaceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['NetworkInterface', function(NetworkInterface) {
                                return NetworkInterface.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('networkInterface', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('networkInterface.delete', {
                parent: 'networkInterface',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/networkInterface/networkInterface-delete-dialog.html',
                        controller: 'NetworkInterfaceDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['NetworkInterface', function(NetworkInterface) {
                                return NetworkInterface.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('networkInterface', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

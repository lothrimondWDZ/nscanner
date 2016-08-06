'use strict';

angular.module('nScannerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('network', {
                parent: 'entity',
                url: '/networks',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.network.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/network/networks.html',
                        controller: 'NetworkController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('network');
                        $translatePartialLoader.addPart('ipaddress');
                        $translatePartialLoader.addPart('iPAddressType');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('network.detail', {
                parent: 'entity',
                url: '/network/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.network.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/network/network-detail.html',
                        controller: 'NetworkDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('network');
                        $translatePartialLoader.addPart('ipaddress');
                        $translatePartialLoader.addPart('iPAddressType');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Network', function($stateParams, Network) {
                        return Network.get({id : $stateParams.id});
                    }]
                }
            })
            .state('network.new', {
                parent: 'network',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/network/network-dialog.html',
                        controller: 'NetworkDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    mask: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('network', null, { reload: true });
                    }, function() {
                        $state.go('network');
                    })
                }]
            })
            .state('network.edit', {
                parent: 'network',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/network/network-dialog.html',
                        controller: 'NetworkDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Network', function(Network) {
                                return Network.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('network', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('network.delete', {
                parent: 'network',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/network/network-delete-dialog.html',
                        controller: 'NetworkDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Network', function(Network) {
                                return Network.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('network', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

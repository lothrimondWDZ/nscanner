'use strict';

angular.module('nScannerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('ipaddress', {
                parent: 'entity',
                url: '/ipaddresss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.ipaddress.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ipaddress/ipaddresss.html',
                        controller: 'IPaddressController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ipaddress');
                        $translatePartialLoader.addPart('iPAddressType');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('ipaddress.detail', {
                parent: 'entity',
                url: '/ipaddress/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.ipaddress.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ipaddress/ipaddress-detail.html',
                        controller: 'IPaddressDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ipaddress');
                        $translatePartialLoader.addPart('iPAddressType');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'IPaddress', function($stateParams, IPaddress) {
                        return IPaddress.get({id : $stateParams.id});
                    }]
                }
            })
            .state('ipaddress.new', {
                parent: 'ipaddress',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/ipaddress/ipaddress-dialog.html',
                        controller: 'IPaddressDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    address: null,
                                    type: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('ipaddress', null, { reload: true });
                    }, function() {
                        $state.go('ipaddress');
                    })
                }]
            })
            .state('ipaddress.edit', {
                parent: 'ipaddress',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/ipaddress/ipaddress-dialog.html',
                        controller: 'IPaddressDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['IPaddress', function(IPaddress) {
                                return IPaddress.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('ipaddress', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('ipaddress.delete', {
                parent: 'ipaddress',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/ipaddress/ipaddress-delete-dialog.html',
                        controller: 'IPaddressDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['IPaddress', function(IPaddress) {
                                return IPaddress.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('ipaddress', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

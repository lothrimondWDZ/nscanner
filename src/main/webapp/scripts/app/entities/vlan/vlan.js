'use strict';

angular.module('nScannerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('vlan', {
                parent: 'entity',
                url: '/vlans',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.vlan.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/vlan/vlans.html',
                        controller: 'VLANController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('vlan');
                        $translatePartialLoader.addPart('vLANType');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('vlan.detail', {
                parent: 'entity',
                url: '/vlan/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.vlan.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/vlan/vlan-detail.html',
                        controller: 'VLANDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('vlan');
                        $translatePartialLoader.addPart('vLANType');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'VLAN', function($stateParams, VLAN) {
                        return VLAN.get({id : $stateParams.id});
                    }]
                }
            })
            .state('vlan.new', {
                parent: 'vlan',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/vlan/vlan-dialog.html',
                        controller: 'VLANDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    number: null,
                                    type: null,
                                    id: null,
                                    networks: [],
                                    vlans: [],
                                    testScripts: []
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('vlan', null, { reload: true });
                    }, function() {
                        $state.go('vlan');
                    })
                }]
            })
            .state('vlan.edit', {
                parent: 'vlan',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/vlan/vlan-dialog.html',
                        controller: 'VLANDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['VLAN', function(VLAN) {
                                return VLAN.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('vlan', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('vlan.delete', {
                parent: 'vlan',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/vlan/vlan-delete-dialog.html',
                        controller: 'VLANDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['VLAN', function(VLAN) {
                                return VLAN.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('vlan', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

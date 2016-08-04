'use strict';

angular.module('nScannerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('networkService', {
                parent: 'entity',
                url: '/networkServices',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.networkService.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/networkService/networkServices.html',
                        controller: 'NetworkServiceController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('networkService');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('networkService.detail', {
                parent: 'entity',
                url: '/networkService/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.networkService.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/networkService/networkService-detail.html',
                        controller: 'NetworkServiceDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('networkService');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'NetworkService', function($stateParams, NetworkService) {
                        return NetworkService.get({id : $stateParams.id});
                    }]
                }
            })
            .state('networkService.new', {
                parent: 'networkService',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/networkService/networkService-dialog.html',
                        controller: 'NetworkServiceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    description: null,
                                    port: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('networkService', null, { reload: true });
                    }, function() {
                        $state.go('networkService');
                    })
                }]
            })
            .state('networkService.edit', {
                parent: 'networkService',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/networkService/networkService-dialog.html',
                        controller: 'NetworkServiceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['NetworkService', function(NetworkService) {
                                return NetworkService.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('networkService', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('networkService.delete', {
                parent: 'networkService',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/networkService/networkService-delete-dialog.html',
                        controller: 'NetworkServiceDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['NetworkService', function(NetworkService) {
                                return NetworkService.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('networkService', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

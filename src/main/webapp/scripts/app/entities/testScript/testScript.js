'use strict';

angular.module('nScannerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('testScript', {
                parent: 'entity',
                url: '/testScripts',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.testScript.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/testScript/testScripts.html',
                        controller: 'TestScriptController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('testScript');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('testScript.detail', {
                parent: 'entity',
                url: '/testScript/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nScannerApp.testScript.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/testScript/testScript-detail.html',
                        controller: 'TestScriptDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('testScript');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'TestScript', function($stateParams, TestScript) {
                        return TestScript.get({id : $stateParams.id});
                    }]
                }
            })
            .state('testScript.new', {
                parent: 'testScript',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/testScript/testScript-dialog.html',
                        controller: 'TestScriptDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    description: null,
                                    cronExpression: null,
                                    path: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('testScript', null, { reload: true });
                    }, function() {
                        $state.go('testScript');
                    })
                }]
            })
            .state('testScript.edit', {
                parent: 'testScript',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/testScript/testScript-dialog.html',
                        controller: 'TestScriptDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['TestScript', function(TestScript) {
                                return TestScript.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('testScript', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('testScript.delete', {
                parent: 'testScript',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/testScript/testScript-delete-dialog.html',
                        controller: 'TestScriptDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['TestScript', function(TestScript) {
                                return TestScript.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('testScript', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

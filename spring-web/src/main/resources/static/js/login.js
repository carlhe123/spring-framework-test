angular.module('login')
    .controller('AppCtrl', ['$scope', '$translate', '$localStorage', '$window', '$modalInstance',
    function ($scope, $translate, $localStorage, $window, $modalInstance) {
        $scope.submit = function(){
            console.log($scope.username);
            console.log($scope.password);
        }
    }]);
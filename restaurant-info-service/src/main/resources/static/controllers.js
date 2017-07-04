angular.module('app.controllers', [])
.controller("myCtrl", ['$scope', "GetRestaurants",
    function ($scope, GetRestaurants) {
        $scope.getAllRestaurants = function () {
            GetRestaurants.getAllRestaurants().then(function(data) {
                $scope.restaurants = data;
            });
        };

        $scope.getRestaurantByName = function () {
            GetRestaurants.getRestaurantByName($scope.name).then(function(data) {
                $scope.restaurants = data;
            });
        }
    }
]);
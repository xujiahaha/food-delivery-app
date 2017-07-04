angular.module('app.services', [])
.service("GetRestaurants", ['$http', function($http) {
    this.getRestaurantByName = function getRestaurantByName(name) {
        var query = '?name='+name;
        return $http({
            method: 'GET',
            url: 'restaurant' + query
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            return response;
        });
    };
    this.getAllRestaurants = function getAllRestaurants() {
        return $http({
            method : 'GET',
            url : 'restaurants'
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            return response;
        });
    };
}]);
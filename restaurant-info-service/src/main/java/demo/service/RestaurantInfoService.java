package demo.service;

import demo.domain.Item;
import demo.domain.Restaurant;

import java.util.List;


public interface RestaurantInfoService {

    List<Restaurant> findAllRestaurant();

    Restaurant findRestaurantById(String restaurantId);

    List<Restaurant> findRestaurantsByNameContains(String restaurantName);

    List<Item> findAllItems(String restaurantId);

    void createRestaurant(Restaurant restaurant);

    void createRestaurants(List<Restaurant> restaurants);



    // TODO
    // get active items only
    // add sorting and pagination
    // find restaurants in one city
    // find restaurants near one location
}

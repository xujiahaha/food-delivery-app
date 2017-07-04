package demo.domain.repository;

import demo.domain.Restaurant;

import java.util.List;

public interface RestaurantRepositoryCustom {

    List<Restaurant> findByNameContains(String restaurantName);

}

package demo.service.impl;

import demo.domain.Item;
import demo.domain.Restaurant;
import demo.domain.repository.RestaurantRepository;
import demo.service.RestaurantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantInfoServiceImpl implements RestaurantInfoService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantInfoServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findAllRestaurant() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public Restaurant findRestaurantById(String restaurantId) {
        return this.restaurantRepository.findOne(restaurantId);
    }

    @Override
    public List<Restaurant> findRestaurantsByNameContains(String restaurantName) {
        return this.restaurantRepository.findByNameContains(restaurantName);
    }

    @Override
    public List<Item> findAllItems(String restaurantId) {
        return this.restaurantRepository.findOne(restaurantId).getItems();
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        this.restaurantRepository.save(restaurant);
    }

    @Override
    public void createRestaurants(List<Restaurant> restaurants) {
        this.restaurantRepository.save(restaurants);
    }
}

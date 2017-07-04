package demo.controller;

import demo.domain.Item;
import demo.domain.Restaurant;
import demo.service.RestaurantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class RestaurantInfoController {

    private RestaurantInfoService restaurantInfoService;

    @Autowired
    public RestaurantInfoController(RestaurantInfoService restaurantInfoService) {
        this.restaurantInfoService = restaurantInfoService;
    }

    // ------------ Get all restaurants ----------------------------------------------------------

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Resource<Restaurant>> getAllRestaurant() {
        return getRestaurantResourceList(this.restaurantInfoService.findAllRestaurant());
    }

    // ------------ Get restaurants by given name ----------------------------------------------------------

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Resource<Restaurant>> getRestaurantByName(@RequestParam(value = "name") String name) {
        return getRestaurantResourceList(this.restaurantInfoService.findRestaurantsByNameContains(name));
    }

    private List<Resource<Restaurant>> getRestaurantResourceList(List<Restaurant> restaurants) {
        List<Resource<Restaurant>> resources = new ArrayList<Resource<Restaurant>>(restaurants.size());
        for(Restaurant restaurant : restaurants) {
            resources.add(getRestaurantResource(restaurant));
        }
        return resources;
    }

    private Resource<Restaurant> getRestaurantResource(Restaurant restaurant) {
        Resource<Restaurant> resource = new Resource<>(restaurant);
        resource.add(linkTo(methodOn(RestaurantInfoController.class).getRestaurantById(restaurant.getId())).withSelfRel());
        return resource;
    }

    // ------------ Get restaurant by id----------------------------------------------------------

    @RequestMapping(value = "/restaurant/{restaurantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Resource<Restaurant> getRestaurantById(@PathVariable("restaurantId") String restaurantId) {
        return getRestaurantResource(this.restaurantInfoService.findRestaurantById(restaurantId));
    }

    // ------------ Get all items in a restaurant given restaurantId ----------------------------------------------------------

    @RequestMapping(value = "/restaurant/{restaurantId}/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Item> getAllItems(@PathVariable("restaurantId") String restaurantId) {
        return this.restaurantInfoService.findAllItems(restaurantId);
    }

    // ------------ upload a restaurant ----------------------------------------------------------

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurant(@RequestBody Restaurant restaurant) {
        this.restaurantInfoService.createRestaurant(restaurant);
    }

    // ------------ upload a list of restaurant ----------------------------------------------------------

    @RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurants(@RequestBody List<Restaurant> restaurants) {
        this.restaurantInfoService.createRestaurants(restaurants);
    }
}

package demo.domain.repository;


import demo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Autowired
    public RestaurantRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Restaurant> findByNameContains(String restaurantName) {
        restaurantName = ".*" + restaurantName + ".*";
        Query query = new Query();
        query.addCriteria(Criteria.where("restaurantName").regex(restaurantName, "i"));
        return mongoTemplate.find(query, Restaurant.class);
    }
}

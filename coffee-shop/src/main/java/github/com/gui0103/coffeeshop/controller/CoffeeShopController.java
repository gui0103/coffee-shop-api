package github.com.gui0103.coffeeshop.controller;

import com.mongodb.client.result.UpdateResult;
import github.com.gui0103.coffeeshop.entity.Coffee;
import github.com.gui0103.coffeeshop.entity.CoffeeShop;
import github.com.gui0103.coffeeshop.repository.CoffeeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffeeShops")
public class CoffeeShopController {

    @Autowired
    private CoffeeShopRepository coffeeShopRepository;

    @Autowired
    private CoffeeController coffeeController;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public List<CoffeeShop> findAllCoffeeShops() {
        return coffeeShopRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CoffeeShop> findCoffeeShopById(@PathVariable String id) {
        return coffeeShopRepository.findById(id);
    }

    @GetMapping("/location/{location}")
    public List<CoffeeShop> findCoffeeShopByLocation(@PathVariable String location) {
        return coffeeShopRepository.findAllByLocation(location);
    }

    @GetMapping("/coffeeList/{id}")
    public List<Coffee> findCoffeeListByID(@PathVariable String id) {
        List<Coffee> coffeeList;
        Optional<CoffeeShop> coffeeShop = findCoffeeShopById(id);
        coffeeList = coffeeShop.get().getCoffeeList();
        return coffeeList;
    }

    @PostMapping
    public void createCoffeeShop(@Valid @RequestBody CoffeeShop coffeeShop) {
        coffeeShopRepository.save(coffeeShop);
    }

    @PatchMapping("/addCoffee/{id}")
    public List<Coffee> addNewCoffee(@Valid @RequestBody Coffee coffee, @PathVariable String id) {
        List<Coffee> coffeeList = findCoffeeListByID(id);
        Optional<CoffeeShop> coffeeShop = findCoffeeShopById(id);
        coffeeList.add(coffee);

        Query query = new Query().addCriteria(Criteria.where("_id").is(coffeeShop.get().getId()));
        Update updateDefinition = new Update().set("coffeeList", coffeeList);

        UpdateResult updateResult = mongoTemplate.upsert(query, updateDefinition, CoffeeShop.class);
        return coffeeList;
    }
}
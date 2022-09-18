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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

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
    public ResponseEntity<List<CoffeeShop>> findAllCoffeeShops() {

        return status(200).body(coffeeShopRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CoffeeShop>> findCoffeeShopById(@PathVariable String id) {

        if (!coffeeShopRepository.existsById(id)) {
            return status(404).build();
        }

        return status(200).body(coffeeShopRepository.findById(id));
    }

    @GetMapping("/location/{location}")
    public List<CoffeeShop> findCoffeeShopByLocation(@PathVariable String location) {
        return coffeeShopRepository.findAllByLocation(location);
    }

    @GetMapping("/coffeeList/{id}")
    public ResponseEntity<List<Coffee>> findCoffeeListByID(@PathVariable String id) {

        if (!coffeeShopRepository.existsById(id)) {
            return status(404).build();
        }

        List<Coffee> coffeeList;
        Optional<CoffeeShop> coffeeShop = coffeeShopRepository.findById(id);
        coffeeList = coffeeShop.get().getCoffeeList();
        return status(200).body(coffeeList);
    }

    @PostMapping
    public void createCoffeeShop(@Valid @RequestBody CoffeeShop coffeeShop) {

        coffeeShopRepository.save(coffeeShop);
    }

    @PatchMapping("/addCoffee/{id}")
    public ResponseEntity<List<Coffee>> addNewCoffee(@Valid @RequestBody Coffee coffee, @PathVariable String id) {
        if (!coffeeShopRepository.existsById(id)) {
            return status(404).build();
        }

        List<Coffee> coffeeList;
        Optional<CoffeeShop> coffeeShop = coffeeShopRepository.findById(id);
        coffeeList = coffeeShop.get().getCoffeeList();
        coffeeList.add(coffee);

        Query query = new Query().addCriteria(Criteria.where("_id").is(coffeeShop.get().getId()));
        Update updateDefinition = new Update().set("coffeeList", coffeeList);

        UpdateResult updateResult = mongoTemplate.upsert(query, updateDefinition, CoffeeShop.class);
        return status(201).body(coffeeList);
    }
}
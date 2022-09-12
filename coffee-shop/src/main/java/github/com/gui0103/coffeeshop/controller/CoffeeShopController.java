package github.com.gui0103.coffeeshop.controller;

import github.com.gui0103.coffeeshop.entity.Coffee;
import github.com.gui0103.coffeeshop.entity.CoffeeShop;
import github.com.gui0103.coffeeshop.repository.CoffeeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffeeShops")
public class CoffeeShopController {

    @Autowired
    private CoffeeShopRepository coffeeShopRepository;

    @Autowired
    private CoffeeController coffeeController;

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
    public void createCoffeeShop(@RequestBody CoffeeShop coffeeShop) {
        coffeeShopRepository.save(coffeeShop);
    }

    //Missing Database Update
    @PatchMapping("/addCoffee/{id}")
    public List<Coffee> addNewCoffee(@RequestBody Coffee coffee, @PathVariable String id) {
        List<Coffee> coffeeList = findCoffeeListByID(id);
        coffeeList.add(coffee);
        return coffeeList;
    }
}
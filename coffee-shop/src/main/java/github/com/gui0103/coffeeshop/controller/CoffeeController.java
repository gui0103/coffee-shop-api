package github.com.gui0103.coffeeshop.controller;

import github.com.gui0103.coffeeshop.repository.CoffeeRepository;
import github.com.gui0103.coffeeshop.entity.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping
    public List<Coffee> findAllCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{name}")
    public Optional<Coffee> findCoffeeByName(@PathVariable String name) {
        return coffeeRepository.findByName(name);
    }

    @PostMapping
    public void createCoffee(@RequestBody Coffee coffee) {
        coffeeRepository.save(coffee);
    }
}
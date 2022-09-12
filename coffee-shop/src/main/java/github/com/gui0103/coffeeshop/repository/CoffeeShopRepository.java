package github.com.gui0103.coffeeshop.repository;

import github.com.gui0103.coffeeshop.entity.CoffeeShop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeShopRepository extends MongoRepository<CoffeeShop, String> {

    List<CoffeeShop> findAll();

    Optional<CoffeeShop> findById(String id);

    List<CoffeeShop> findAllByLocation(String location);
}

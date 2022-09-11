package github.com.gui0103.coffeeshop.repository;

import github.com.gui0103.coffeeshop.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findById(String idUser);

    List<User> findAll();

}

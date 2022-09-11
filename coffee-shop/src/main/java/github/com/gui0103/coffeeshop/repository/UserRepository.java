package github.com.gui0103.coffeeshop.repository;

import github.com.gui0103.coffeeshop.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findById(UUID idUser);

    List<User> findAll();

}

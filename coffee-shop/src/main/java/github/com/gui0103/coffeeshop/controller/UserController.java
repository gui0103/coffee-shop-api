package github.com.gui0103.coffeeshop.controller;

import github.com.gui0103.coffeeshop.entity.User;
import github.com.gui0103.coffeeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {

        return status(200).body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable String id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        return status(200).body(userRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user) {

        userRepository.save(user);

        return status(201).build();
    }
}

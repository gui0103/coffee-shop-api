package github.com.gui0103.coffeeshop.controller;

import github.com.gui0103.coffeeshop.entity.User;
import github.com.gui0103.coffeeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{idUser}")
    public Optional<User> findUserById(@PathVariable UUID idUser) {
        return userRepository.findById(idUser);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }
}

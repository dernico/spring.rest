package schlingel.controller;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import schlingel.domain.Person;
import schlingel.domain.User;
import schlingel.dto.UserDto;
import schlingel.repository.UserRepository;

import java.security.Principal;

@RestController
public class PersonController {

    private UserRepository userRepository;

    public PersonController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @GetMapping("/me")
    public Principal me(Principal principal){
        return principal;
    }

    @PostMapping("/user")
    public User CreateUser(@RequestBody UserDto user){

        User newUser = user.toDomain();

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encryptedPassword = encoder.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);


        this.userRepository.save(newUser);
        return newUser;
    }
}

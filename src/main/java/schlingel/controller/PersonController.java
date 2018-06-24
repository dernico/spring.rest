package schlingel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class PersonController {

    @GetMapping("/me")
    public Principal me(Principal principal){
        return principal;
    }
}

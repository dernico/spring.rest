package schlingel.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import schlingel.domain.User;
import schlingel.repository.UserRepository;

import java.security.Principal;

@Service
public class CurrentUserService implements ICurrentUserService {

    private UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUserFromRequest() {

            Principal principal = SecurityContextHolder.getContext().getAuthentication();
            String username = principal.getName();
            User user = userRepository.findByUsername(username);
            return user;
    }
}

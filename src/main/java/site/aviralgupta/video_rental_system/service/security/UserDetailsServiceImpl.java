package site.aviralgupta.video_rental_system.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.aviralgupta.video_rental_system.entity.User;
import site.aviralgupta.video_rental_system.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> foundUserOptional = userRepository.findByEmail(email);

        if(foundUserOptional.isEmpty())
            throw new UsernameNotFoundException("Invalid username or password");

        User user = foundUserOptional.get();

        return new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRole());
    }
}

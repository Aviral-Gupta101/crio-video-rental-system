package site.aviralgupta.video_rental_system.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import site.aviralgupta.video_rental_system.component.JwtUtil;
import site.aviralgupta.video_rental_system.dto.UserLoginDto;
import site.aviralgupta.video_rental_system.dto.UserRegisterDto;
import site.aviralgupta.video_rental_system.entity.User;
import site.aviralgupta.video_rental_system.exceptions.exceptions.UserAlreadyExistsException;
import site.aviralgupta.video_rental_system.repository.UserRepository;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, ModelMapper mapper, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public void signup(UserRegisterDto dto){

        Optional<User> foundUser = userRepository.findByEmail(dto.getEmail());

        if(foundUser.isPresent())
            throw new UserAlreadyExistsException("User is already exists");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(7);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User newUser = mapper.map(dto, User.class);

        userRepository.save(newUser);
    }

    public String verify(UserLoginDto dto) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }
}

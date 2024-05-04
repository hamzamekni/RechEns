package com.example.resens.service;
import com.example.resens.config.PasswordEncoder;
import com.example.resens.dto.AuthenticationResponse;
import com.example.resens.dto.AuthenticationRequest;
import com.example.resens.dto.ProfileResponse;
import com.example.resens.dto.RegisterRequest;
import com.example.resens.enumeration.Role;
import com.example.resens.model.User;
import com.example.resens.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.example.resens.exceptions.UserException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private static final String CONFIRMATION_URL = "http://localhost:8080/user/ConfirmAccount/%s";
    private final PasswordEncoder passwordEncoder;
    private final EmailRegistrationService emailRegistrationService;
    private final JwtService jwtService;
    private final RedisService redisService;
    public void saveAccount(User user){
         userRepository.save(user);
    }
    @Transactional
    public void registerAccount(RegisterRequest request, Role role) {
        boolean userExists = userRepository.findByEmail(request.getEmail()).isPresent();
        if (userExists) {
            throw new UserException("A user already exists with the same email");
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .birthday(request.getBirthday())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.bCryptPasswordEncoder().encode(request.getPassword()))
                .role(role)
                .enabled(false)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.genToken(user,new HashMap<>());
        try {
            emailRegistrationService.send(
                    user.getEmail(),
                    user.getFirstName(),
                    "confirm-email",
                    String.format(CONFIRMATION_URL, jwtToken)
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public String confirmAccount(String token) {

        String userEmail = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()->new UserException("User not found"+ userEmail));
        if (!user.isEnabled()) {
            user.setEnabled(true);
            userRepository.save(user);
            return "success";
        }
        else if (!user.isEnabled() && jwtService.isTokenExpired(token)){
            return handleExpiredToken(userEmail,token);
        }
        else {
            return "already";
        }
    }

    private String handleExpiredToken(String userEmail, String token) {
        String RefreshedToken = jwtService.refreshExpiredToken(token);
        redisService.removeToken(userEmail);
        redisService.storeToken(RefreshedToken, userEmail);
        var user = userRepository.getUserByEmail(userEmail);
        try {
            emailRegistrationService.send(
                    userEmail,
                    user.getFirstName(),
                    null,
                    String.format(CONFIRMATION_URL, RefreshedToken)
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return  "Token expired, a new token has been sent to your email";
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            throw new UserException(e.getMessage());
        }
        var user = userRepository.getUserByEmail(request.getEmail());
        // Generate JWT token and build the response
        Map<String, String> map = new HashMap<>();
        map.put("role", user.getRole().name());
        var jwtToken = jwtService.genToken(user, map);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .email(user.getEmail())
                .messageResponse("You have been successfully authenticated!")
                .build();
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
    public ProfileResponse uploadImage(User user, MultipartFile file) throws IOException {
        user.setProfileImage(file.getBytes());
        User updatdUser = userRepository.save(user);
        return ProfileResponse.fromEntity(updatdUser);
    }
    public void updateUser(User user)  {
        userRepository.save(user);
    }
    public ProfileResponse getProfile(Authentication authentication)
    {
        var user = userRepository.getUserByEmail(authentication.getName());
        return ProfileResponse.fromEntity(user);

    }
    public ProfileResponse updateProfile(User userProfile)  {
        User user = userRepository.save(userProfile);
        return ProfileResponse.fromEntity(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

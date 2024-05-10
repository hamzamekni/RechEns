package com.example.resens.service;
import com.example.resens.config.PasswordEncoder;
import com.example.resens.dto.*;
import com.example.resens.enumeration.Role;
import com.example.resens.enumeration.Statut_Etude_Presentiel;
import com.example.resens.exceptions.TeacherException;
import com.example.resens.model.Teacher;
import com.example.resens.model.User;
import com.example.resens.repository.TeacherRepository;
import com.example.resens.repository.UserRepository;
import com.example.resens.util.FileUtils;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.example.resens.exceptions.UserException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final AuthenticationManager authenticationManager;
    private static final String CONFIRMATION_URL = "http://localhost:8081/user/ConfirmAccount/%s";
    private static final String CONFIRM_TEACHER_URL = "http://localhost:8081/user/ConfirmTeacher/%s";
    private static final String DELETE_TEACHER_URL = "http://localhost:8081/teachers/deleteTeacher/%s";

    @Autowired
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

    @Transactional
    public void registerTeacher(RegisterTeacherRequest request, Role role, MultipartFile file) throws IOException {
        boolean userExists = userRepository.findByEmail(request.getEmail()).isPresent();
        if (userExists) {
            throw new UserException("A user already exists with the same email");
        }
        var teacher = Teacher.builder()
                .firstName(request.getFirstName())
                .email(request.getEmail())
                .montant_non_paye(request.getMontantNonPaye())
                .statut_etude_presentiel(request.getStatut_etude_presentiel())
                .detailEtudePresentiel(request.getDetailEtudePresentiel())
                .detailEnseigant(request.getDetailEnseigant())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .enabled(false)
                .filePath(FileUtils.compressImage(file.getBytes()))
                .build();


        teacherRepository.save(teacher);
        var jwtToken = jwtService.genToken(teacher,new HashMap<>());
        try {
            emailRegistrationService.sendToTeacher(
                    "hamzamekni4@gmail.com",
                    teacher.getFirstName(),
                    teacher.getEmail(),
                    teacher.getDetailEnseigant(),
                    teacher.getPhoneNumber(),
                    teacher.getMatieres(),
                    "confirm-teacher",
                    String.format(CONFIRM_TEACHER_URL, jwtToken),
                    teacher.getTeacherId(),
                    DELETE_TEACHER_URL
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public String confirmTeacher(String token) {

        String userEmail = jwtService.extractUsername(token);
        Teacher teacher = teacherRepository.findByEmail(userEmail)
                .orElseThrow(()->new TeacherException("Teacher not found"+ userEmail));
        if (!teacher.isEnabled()) {
            teacher.setEnabled(true);
            teacher.setStatut_etude_presentiel(Statut_Etude_Presentiel.valueOf("ACCEPTED"));
            teacherRepository.save(teacher);
            var user = User.builder()
                    .firstName(teacher.getFirstName())
                    .lastName(teacher.getFirstName())
                    .email(teacher.getEmail())
                    .phoneNumber(teacher.getPhoneNumber())
                    .password(passwordEncoder.bCryptPasswordEncoder().encode(passwordEncoder.decodePassword(teacher.getPassword())))
                    .role(teacher.getRole())
                    .enabled(teacher.isEnabled())
                    .build();
            userRepository.save(user);
            return "successfully";

        }
        else if (!teacher.isEnabled() && jwtService.isTokenExpired(token)){
            return handleExpiredToken(userEmail,token);
        }
        else {
            return "already";
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

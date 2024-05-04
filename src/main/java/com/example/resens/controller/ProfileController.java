package com.example.resens.controller;

import com.example.resens.model.User;
import com.example.resens.dto.ProfileResponse;
import com.example.resens.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    @GetMapping("/getProfile")
    public ResponseEntity<ProfileResponse> getUserProfile(Authentication authentication) {
        ProfileResponse profileDto = userService.getProfile(authentication);
        return ResponseEntity.ok(profileDto);
    }
    @PutMapping("/updateProfile")
    public ResponseEntity<ProfileResponse> updateUserProfile(Authentication authentication, @RequestBody ProfileResponse profileDto) {
        User user = userService.getUserByEmail(authentication.getName());
        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        user.setBirthday(profileDto.getBirthday());
        user.setPhoneNumber(profileDto.getPhoneNumber());
        ProfileResponse updatedProfile = userService.updateProfile(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedProfile);
    }
    @PutMapping("/upload-image")
    public ResponseEntity<ProfileResponse> uploadImage(Authentication authentication, @RequestParam("file") MultipartFile file) throws IOException {
        User user = userService.getUserByEmail(authentication.getName());
        ProfileResponse updatedProfile = userService.uploadImage(user, file);
        return ResponseEntity.ok(updatedProfile);
    }
}

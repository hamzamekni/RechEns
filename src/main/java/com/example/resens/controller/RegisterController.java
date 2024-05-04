package com.example.resens.controller;
import com.example.resens.dto.ConfirmationResponse;
import com.example.resens.dto.RegisterRequest;
import com.example.resens.dto.RegisterResponse;
import com.example.resens.enumeration.Role;
import com.example.resens.exceptions.UserException;
import com.example.resens.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    @PostMapping("/Register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse  = new RegisterResponse();
        try {
            userService.registerAccount(registerRequest, Role.ROLE_USER);
            registerResponse.setEmailResponse(registerRequest.getEmail());
            registerResponse.setMessageResponse("Account Created");
            return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
        }catch (UserException e) {
            registerResponse.setMessageResponse(e.getMessage());
            registerResponse.setEmailResponse(registerRequest.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registerResponse);
        } catch (Exception e) {
            registerResponse.setMessageResponse("An error occurred while registering user.");
            registerResponse.setEmailResponse(registerRequest.getEmail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(registerResponse);
        }
    }
    @GetMapping("/ConfirmAccount/{token}")
    public ResponseEntity<ConfirmationResponse> confirmUser(@PathVariable String token) {
        ConfirmationResponse confirmationMessage = new ConfirmationResponse();
        try {
            String response = userService.confirmAccount(token);
            if (response.equals("success")) {
                confirmationMessage.setMessageResponse("Email Confirmed Successfully.");
            }
            else if (response.equals("already")){
                confirmationMessage.setMessageResponse("You Have Already Confirmed Your Email.");
            }
            else{
                confirmationMessage.setMessageResponse(response);
            }
            confirmationMessage.setConfirmedEmail(true);
            return ResponseEntity.ok(confirmationMessage);
        }catch (UserException e) {
            confirmationMessage.setMessageResponse(e.getMessage());
            confirmationMessage.setConfirmedEmail(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(confirmationMessage);
        } catch (Exception e) {
            confirmationMessage.setMessageResponse(e.getMessage());
            confirmationMessage.setConfirmedEmail(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(confirmationMessage);
        }
    }


}

package com.example.resens.config;
import com.example.resens.enumeration.Role;
import com.example.resens.model.User;
import com.example.resens.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Initialize implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        try {
            createAdminUserIfNeeded();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    private void createAdminUserIfNeeded() {
        User adminUser = userService.getUserByEmail("admin@admin.com");
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setFirstName("admin");
            adminUser.setLastName("admin");
            adminUser.setEmail("admin@admin.com");
            adminUser.setPhoneNumber(88888888);
            adminUser.setBirthday(new Date());
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setRole(Role.ROLE_ADMIN);
            adminUser.setEnabled(true);
            userService.saveAccount(adminUser);
        }
    }


}

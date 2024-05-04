package com.example.resens.repository;

import com.example.resens.enumeration.Role;
import com.example.resens.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    User getUserByEmail(String email);

    List<User> findUserByRole(Role role);





}

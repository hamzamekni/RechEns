package com.example.resens.repository;

import com.example.resens.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
    Adress findByAdressId(Long adressId);
}
package com.example.resens.repository;

import com.example.resens.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere,Long> {

    Matiere findByMatiereId(Long matiereId);
}

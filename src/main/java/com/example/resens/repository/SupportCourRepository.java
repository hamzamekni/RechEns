package com.example.resens.repository;

import com.example.resens.model.SupportCour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportCourRepository extends JpaRepository<SupportCour,Long> {
}

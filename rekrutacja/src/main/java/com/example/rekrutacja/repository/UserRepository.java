package com.example.rekrutacja.repository;

import com.example.rekrutacja.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Userr, Long> {

    Userr findByEmail(String email);
}

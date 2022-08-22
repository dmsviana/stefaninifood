package com.stefanini.StefaniniFood.repository;

import com.stefanini.StefaniniFood.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyModel, UUID> {


    Optional<CompanyModel> findByEmail(String email);
    Optional<CompanyModel> findByLogin(String login);
}

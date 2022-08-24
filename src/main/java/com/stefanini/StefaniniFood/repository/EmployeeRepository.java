package com.stefanini.StefaniniFood.repository;

import com.stefanini.StefaniniFood.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID> {

        @Override
        Optional<EmployeeModel> findById(UUID id);

        @Query("from EmployeeModel employee inner join fetch employee.company where employee.company.id = :company_id")
        List<EmployeeModel> findByCompanyId(@Param("company_id") UUID companyId);
}

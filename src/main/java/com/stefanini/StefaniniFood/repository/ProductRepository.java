package com.stefanini.StefaniniFood.repository;

import com.stefanini.StefaniniFood.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    @Override
    Optional<ProductModel> findById(UUID uuid);

    @Query("from ProductModel product inner join fetch product.company where product.company.id = :company_id")
    List<ProductModel> findByCompanyId(@Param("company_id") UUID companyId);
}

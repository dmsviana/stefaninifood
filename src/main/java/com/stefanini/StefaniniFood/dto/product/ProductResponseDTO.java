package com.stefanini.StefaniniFood.dto.product;


import com.stefanini.StefaniniFood.model.ProductModel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO {

    private UUID id;
    private String productName;
    private Double price;
    private String description;


    public static ProductResponseDTO convertToDto(ProductModel product){
        var productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setProductName(product.getProductName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setDescription(product.getDescription());

        return productResponseDTO;
    }



}

package com.stefanini.StefaniniFood.service;

import com.stefanini.StefaniniFood.dto.product.ProductRequestDTO;
import com.stefanini.StefaniniFood.dto.product.UpdateProductDTO;
import com.stefanini.StefaniniFood.model.ProductModel;

import java.util.List;

public interface ProductService {

    ProductModel saveProduct(ProductRequestDTO product, String companyId);

    ProductModel getProduct(String productId);

    List<ProductModel> listProductByCompanyId(String productId);

    ProductModel updateProduct(String productid, UpdateProductDTO updateProductDTO);

    ProductModel deleteProduct(String productId);
}

package com.stefanini.StefaniniFood.service.impl;

import com.stefanini.StefaniniFood.dto.product.ProductRequestDTO;
import com.stefanini.StefaniniFood.dto.product.UpdateProductDTO;
import com.stefanini.StefaniniFood.exceptions.CompanyNotFoundException;
import com.stefanini.StefaniniFood.exceptions.ProductAlreadyExistsException;
import com.stefanini.StefaniniFood.exceptions.ProductNotFoundException;
import com.stefanini.StefaniniFood.model.CompanyModel;
import com.stefanini.StefaniniFood.model.ProductModel;
import com.stefanini.StefaniniFood.repository.CompanyRepository;
import com.stefanini.StefaniniFood.repository.ProductRepository;
import com.stefanini.StefaniniFood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;
    private CompanyRepository companyRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductModel saveProduct(ProductRequestDTO productRequestDTO, String companyId) {
        Optional<CompanyModel> company = this.companyRepository.findById(UUID.fromString(companyId));
        if (company.isEmpty()) {
            throw new CompanyNotFoundException("Estabelecimento não encontrado!");
        }
        List<ProductModel> productList = this.productRepository.findByCompanyId(UUID.fromString(companyId));
        if (!productList.isEmpty()) {
            for (ProductModel productModel : productList) {
                if (Objects.equals(productModel.getProductName(), productRequestDTO.getProductName())) {
                    throw new ProductAlreadyExistsException("Produto já cadastrado! Tente novamente mudando o nome do produto");
                }
            }
        }
        var productModel = new ProductModel();

        productModel.setProductName(productRequestDTO.getProductName());
        productModel.setPrice(productRequestDTO.getPrice());
        productModel.setDescription(productRequestDTO.getDescription());
        productModel.setActive(productRequestDTO.isActive());
        productModel.setCompany(company.get());

        ProductModel productSaved = this.productRepository.save(productModel);

        return getProduct(String.valueOf(productSaved.getId()));
    }

    @Override
    public ProductModel getProduct(String productId) {
        Optional<ProductModel> product = this.productRepository.findById(
                UUID.fromString(productId));
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Produto não encontrado!");
        }
        return product.get();
    }

    @Override
    public List<ProductModel> listProductByCompanyId(String companyId) {
        List<ProductModel> productList = this.productRepository.findByCompanyId(
                UUID.fromString(companyId));
        if (productList.isEmpty()) {
            throw new ProductNotFoundException("Estabelecimento sem produtos!");
        }
        return productList;
    }

    @Override
    public ProductModel updateProduct(String productId, UpdateProductDTO updateProductDTO) {
        var productAlreadyExists = this.productRepository.findById(
                UUID.fromString(productId));
        if (productAlreadyExists.isEmpty()) {
            throw new ProductNotFoundException("Produto não encontrado!");
        }
        List<ProductModel> productList = this.productRepository.findByCompanyId(
                productAlreadyExists.get().getCompany().getId());
        if (!productList.isEmpty()) {
            for (ProductModel productModel : productList) {
                if (Objects.equals(productModel.getProductName(), productAlreadyExists.get().getProductName())) {
                    throw new ProductAlreadyExistsException("Produto já cadastrado! Tente novamente mudando o nome do produto");
                }
            }
        }

        var product = productAlreadyExists.get();

        product.setProductName(updateProductDTO.getProductName());
        product.setPrice(updateProductDTO.getPrice());
        product.setDescription(updateProductDTO.getDescription());
        product.setActive(updateProductDTO.isActive());

        this.productRepository.save(product);
        return product;
    }

    @Override
    public ProductModel deleteProduct(String productId) {
        var productAlreadyExists = this.productRepository.findById(UUID.fromString(productId));
        if(productAlreadyExists.isEmpty()){
            throw new ProductNotFoundException("Produto não encontrado!");
        }

        this.productRepository.deleteById(productAlreadyExists.get().getId());
        return null;
    }
}

package com.stefanini.StefaniniFood.controllers.product;

import com.stefanini.StefaniniFood.dto.product.ProductRequestDTO;
import com.stefanini.StefaniniFood.dto.product.ProductResponseDTO;
import com.stefanini.StefaniniFood.dto.product.UpdateProductDTO;
import com.stefanini.StefaniniFood.model.ProductModel;
import com.stefanini.StefaniniFood.service.impl.ProductServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(value = "/products")
public class ProductController {

    private ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl ProductService) {
        this.productService = ProductService;
    }

    @ApiOperation("This method is used to save a product.")
    @PostMapping("/{companyId}")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody @Valid ProductRequestDTO product, @PathVariable String companyId) {
        ProductModel productSaved = this.productService.saveProduct(product, companyId.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.convertToDto(productSaved));
    }

    @ApiOperation("This method is used to list a product's data by id.")
    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String productId) {
        ProductModel product = this.productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(ProductResponseDTO.convertToDto(product));
    }

    @ApiOperation("This method is used to list a company products by company id.")
    @GetMapping("/searchProducts/{companyId}")
    public ResponseEntity<List<ProductResponseDTO>> listProductByCompanyId(@PathVariable String companyId) {
        List<ProductModel> productList = this.productService.listProductByCompanyId(companyId);
        List<ProductResponseDTO> responseList = new ArrayList<>();
        for (ProductModel product : productList) {
            responseList.add(ProductResponseDTO.convertToDto(product));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @ApiOperation("This method is used to update a product's data.")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody @Valid UpdateProductDTO productDTO,
                                                            @PathVariable String productId) {

        ProductModel product = this.productService.updateProduct(productId, productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ProductResponseDTO.convertToDto(product));
    }

    @ApiOperation("This method is used to delete a product")
    @DeleteMapping("delete/{productId}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable String productId) {

        ProductModel product = this.productService.deleteProduct(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.OK).body(ProductResponseDTO.convertToDto(new ProductModel()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProductResponseDTO.convertToDto(product));

    }



}

package com.stefanini.StefaniniFood.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO {

    @NotBlank(message = "O campo nome do produto não pode ser vazio!")
    private String productName;

    @NotNull(message = "O campo preço do produto não pode ser vazio!")
    private Double price;

    @NotBlank(message = "O campo descrição do produto não pode ser vazio!")
    private String description;

    @NotNull(message = "A atividade do produto precisa ser especificada!")
    private boolean active;

}
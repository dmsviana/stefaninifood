package com.stefanini.StefaniniFood.dto.company;

import com.stefanini.StefaniniFood.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {

    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String name;

    @NotBlank(message = "O campo email não pode ser vazio!")
    @Email(message = "O campo email deve ter o formato de email!")
    private String email;

    @NotBlank(message = "O campo login não pode ser vazio!")
    private String login;

    @NotBlank(message = "O campo CNPJ não pode ser vazio!")
    private String cnpj;

    @NotBlank(message = "O campo senha não pode ser vazio!")
    @Size(min = 8, max = 16, message = "A senha deve possuir no mínimo 8 e no máximo 16 caracteres!")
    private String password;

    @NotBlank(message = "O campo celular não pode ser vazio!")
    private String cellphone;

    @NotBlank(message = "O campo CEP não pode ser vazio!")
    private String postalCode;

    @NotBlank(message = "O campo Estado não pode ser vazio!")
    private String state;

    @NotBlank(message = "O campo cidade não pode ser vazio!")
    private String city;

    @NotBlank(message = "O campo bairro não pode ser vazio!")
    private String district;

    @NotBlank(message = "O campo rua não pode ser vazio!")
    private String street;

    @NotBlank(message = "O campo número não pode ser vazio!")
    private String number;

    private String referencePoint;

    private List<ProductModel> products;

}


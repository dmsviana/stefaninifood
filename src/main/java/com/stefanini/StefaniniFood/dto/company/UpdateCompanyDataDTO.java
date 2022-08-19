package com.stefanini.StefaniniFood.dto.company;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyDataDTO {

    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String name;

    @NotBlank(message = "O campo telefone não pode ser vazio!")
    private String cellphone;

    @NotBlank(message = "O campo email não pode ser vazio!")
    private String email;



}

package com.stefanini.StefaniniFood.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeDTO {

    @NotBlank(message = "O campo email não pode ser vazio!")
    private String email;

    @NotBlank(message = "O campo telefone não pode ser vazio!")
    private String cellphone;

}

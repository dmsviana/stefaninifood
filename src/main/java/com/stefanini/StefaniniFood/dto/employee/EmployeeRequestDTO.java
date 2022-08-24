package com.stefanini.StefaniniFood.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {

    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String name;

    @NotBlank(message = "O campo email não pode ser vazio!")
    @Email(message = "O campo email deve ter o formato de email!")
    private String email;

    @NotBlank(message = "O campo login não pode ser vazio!")
    private String login;

    @NotBlank(message = "O campo CPF não pode ser vazio!")
    private String cpf;

    @NotBlank(message = "O campo senha não pode ser vazio!")
    @Size(min = 8, max = 16, message = "A senha deve possuir no mínimo 8 e no máximo 16 caracteres!")
    private String password;

    @NotBlank(message = "O campo celular não pode ser vazio!")
    private String cellphone;


}

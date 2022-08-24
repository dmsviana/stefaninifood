package com.stefanini.StefaniniFood.dto.employee;


import com.stefanini.StefaniniFood.model.EmployeeModel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private String login;
    private String cpf;
    private String cellphone;

    public static EmployeeResponseDTO convertToDto(EmployeeModel employee){
        var employeeResponseDTO = new EmployeeResponseDTO();

        employeeResponseDTO.setId(employee.getId());
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setEmail(employee.getEmail());
        employeeResponseDTO.setLogin(employee.getLogin());
        employeeResponseDTO.setCpf(employee.getCpf());
        employeeResponseDTO.setCellphone(employee.getCellphone());
        return employeeResponseDTO;
    }

}

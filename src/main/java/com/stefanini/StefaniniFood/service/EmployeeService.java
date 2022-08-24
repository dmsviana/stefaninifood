package com.stefanini.StefaniniFood.service;

import com.stefanini.StefaniniFood.dto.employee.UpdateEmployeeDTO;
import com.stefanini.StefaniniFood.dto.employee.EmployeeRequestDTO;
import com.stefanini.StefaniniFood.model.EmployeeModel;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeService {



    EmployeeModel saveEmployee(EmployeeRequestDTO employeeRequestDTO, String companyId);

    EmployeeModel listEmployee(String employeeId);

    List<EmployeeModel> listEmployeeByCompanyId(String companyId);

    EmployeeModel updateEmployee(String employeeId, UpdateEmployeeDTO updateEmployeeDTO);

    EmployeeModel deleteEmployee(String employeeId);
    
}

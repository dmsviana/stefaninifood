package com.stefanini.StefaniniFood.service.impl;

import com.stefanini.StefaniniFood.dto.employee.EmployeeRequestDTO;
import com.stefanini.StefaniniFood.dto.employee.UpdateEmployeeDTO;
import com.stefanini.StefaniniFood.exceptions.CompanyNotFoundException;
import com.stefanini.StefaniniFood.exceptions.EmployeeAlreadyExistsException;
import com.stefanini.StefaniniFood.exceptions.EmployeeNotFoundException;
import com.stefanini.StefaniniFood.exceptions.ProductAlreadyExistsException;
import com.stefanini.StefaniniFood.model.CompanyModel;
import com.stefanini.StefaniniFood.model.EmployeeModel;
import com.stefanini.StefaniniFood.repository.CompanyRepository;
import com.stefanini.StefaniniFood.repository.EmployeeRepository;
import com.stefanini.StefaniniFood.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;
    ;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public EmployeeModel saveEmployee(EmployeeRequestDTO employeeRequestDTO, String companyId) {
        Optional<CompanyModel> company = this.companyRepository.findById(UUID.fromString(companyId));
        if (company.isEmpty()) {
            throw new CompanyNotFoundException("Estabelecimento não encontrado!");
        }

        List<EmployeeModel> employeeList = this.employeeRepository.findByCompanyId(UUID.fromString(companyId));
        if (!employeeList.isEmpty()) {
            for (EmployeeModel employeeModel : employeeList) {
                if (Objects.equals(employeeModel.getCpf(), employeeRequestDTO.getCpf())) {
                    throw new EmployeeAlreadyExistsException("Funcionário já cadastrado!");
                }
            }
        }
        var employeeModel = new EmployeeModel();
        employeeModel.setEmail(employeeRequestDTO.getEmail());
        employeeModel.setName(employeeRequestDTO.getName());
        employeeModel.setLogin(employeeRequestDTO.getLogin());
        employeeModel.setPassword(passwordEncoder.encode(employeeRequestDTO.getPassword()));
        employeeModel.setCpf(employeeRequestDTO.getCpf());
        employeeModel.setCellphone(employeeRequestDTO.getCellphone());
        employeeModel.setCompany(company.get());


        EmployeeModel savedEmployee = this.employeeRepository.save(employeeModel);
        return this.employeeRepository.save(savedEmployee);
    }

    @Override
    public EmployeeModel listEmployee(String employeeId) {
        Optional<EmployeeModel> employee = this.employeeRepository.findById(UUID.fromString(employeeId));
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Funcionário não encontrado!");
        }
        return employee.get();
    }

    @Override
    public List<EmployeeModel> listEmployeeByCompanyId(String companyId) {
        List<EmployeeModel> employees = this.employeeRepository.findByCompanyId(UUID.fromString(companyId));
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Estabelecimento sem funcionários!");
        }

        return employees;
    }

    @Override
    public EmployeeModel updateEmployee(String employeeId, UpdateEmployeeDTO updateEmployeeDTO) {
        var employeeAlreadyExists = this.employeeRepository.findById(UUID.fromString(employeeId));
        if (employeeAlreadyExists.isEmpty()) {
            throw new EmployeeNotFoundException("Funcionário não encontrado!");
        }

        var employee = employeeAlreadyExists.get();
        employee.setEmail(updateEmployeeDTO.getEmail());
        employee.setCellphone(updateEmployeeDTO.getCellphone());

        this.employeeRepository.save(employee);
        return employee;
    }
    @Override
    public EmployeeModel deleteEmployee(String employeeId) {
        var employeeAlreadyExists = this.employeeRepository.findById(UUID.fromString(employeeId));
        if (employeeAlreadyExists.isEmpty()) {
            throw new EmployeeNotFoundException("Funcionário não encontrado!");
        }
        this.employeeRepository.deleteById(employeeAlreadyExists.get().getId());
        return null;
    }
}

package com.stefanini.StefaniniFood.controllers.employee;


import com.stefanini.StefaniniFood.dto.employee.EmployeeRequestDTO;
import com.stefanini.StefaniniFood.dto.employee.EmployeeResponseDTO;
import com.stefanini.StefaniniFood.dto.employee.UpdateEmployeeDTO;
import com.stefanini.StefaniniFood.dto.product.ProductResponseDTO;
import com.stefanini.StefaniniFood.dto.product.UpdateProductDTO;
import com.stefanini.StefaniniFood.model.EmployeeModel;
import com.stefanini.StefaniniFood.model.ProductModel;
import com.stefanini.StefaniniFood.service.impl.EmployeeServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation("Este método é usado para salvar um funcionário.")
    @PostMapping("/{companyId}")
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@RequestBody @Valid EmployeeRequestDTO employee, @PathVariable String companyId) {
        EmployeeModel employeeSaved = this.employeeService.saveEmployee(employee, companyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeResponseDTO.convertToDto(employeeSaved));
    }

    @ApiOperation("Este método é usado para listar um funcionário a partir de seu id.")
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> listEmployeeById(@PathVariable String employeeId) {
        EmployeeModel employee = this.employeeService.listEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(EmployeeResponseDTO.convertToDto(employee));
    }

    @ApiOperation("Este método é usado para listar funcionários de um estabelecimento a partir do id do estabelecimento.")
    @GetMapping("/searchEmployees/{companyId}")
    public ResponseEntity<List<EmployeeResponseDTO>> listEmployeeByCompanyId(@PathVariable String companyId) {
        List<EmployeeModel> employeeList = this.employeeService.listEmployeeByCompanyId(companyId);
        List<EmployeeResponseDTO> responseList = new ArrayList<>();
        for (EmployeeModel employee : employeeList) {
            responseList.add(EmployeeResponseDTO.convertToDto(employee));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @ApiOperation("Este método é usado para atualizar dados de um funcionário.")
    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@RequestBody @Valid UpdateEmployeeDTO employeeDTO,
                                                            @PathVariable String employeeId) {

        EmployeeModel employee = this.employeeService.updateEmployee(employeeId, employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(EmployeeResponseDTO.convertToDto(employee));
    }


    @ApiOperation("Este método é usado para deletar um funcionário.")
    @DeleteMapping("delete/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@PathVariable String employeeId) {

        EmployeeModel employee = this.employeeService.deleteEmployee(employeeId);
        if(employee == null) {
            return ResponseEntity.status(HttpStatus.OK).body(EmployeeResponseDTO.convertToDto(new EmployeeModel()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(EmployeeResponseDTO.convertToDto(employee));

    }




}

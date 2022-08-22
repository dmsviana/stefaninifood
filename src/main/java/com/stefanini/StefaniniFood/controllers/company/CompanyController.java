package com.stefanini.StefaniniFood.controllers.company;


import com.stefanini.StefaniniFood.dto.company.CompanyRequestDTO;
import com.stefanini.StefaniniFood.dto.company.CompanyResponseDTO;
import com.stefanini.StefaniniFood.dto.company.UpdateCompanyDataDTO;
import com.stefanini.StefaniniFood.dto.product.ProductResponseDTO;
import com.stefanini.StefaniniFood.model.CompanyModel;
import com.stefanini.StefaniniFood.model.ProductModel;
import com.stefanini.StefaniniFood.service.impl.CompanyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(value = "/company")
public class CompanyController {

    private CompanyServiceImpl companyService;
    @Autowired
    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @ApiOperation("Este método é usado para salvar um estabelecimento.")
    @PostMapping()
    public ResponseEntity<CompanyResponseDTO> saveCompany(@RequestBody @Valid CompanyRequestDTO company) {
        CompanyModel companySaved = this.companyService.saveCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(CompanyResponseDTO.convertToDto(companySaved));
    }


    @ApiOperation("Este método é usado para listar todos os estabelecimentos.")
    @GetMapping("/get-all")
    public ResponseEntity<List<CompanyResponseDTO>> listAllCompany() {

        List<CompanyModel> companyList = this.companyService.listAllCompany();
        List<CompanyResponseDTO> responseList = new ArrayList<>();
        for (CompanyModel companyModel : companyList) {
            responseList.add(CompanyResponseDTO.convertToDto(companyModel));
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @ApiOperation("Este método é usado para atualizar dados de um estabelecimento.")
    @PatchMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@RequestBody @Valid UpdateCompanyDataDTO address, @PathVariable String companyId) {
        CompanyModel company = this.companyService.updateCompany(companyId, address);
        return ResponseEntity.status(HttpStatus.OK).body(CompanyResponseDTO.convertToDto(company));
    }

    @ApiOperation("Este método é usado para listar dados de um estabelecimento a partir de seu id.")
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> listCompanyById(
            @PathVariable String companyId
    ) {
        CompanyModel company = this.companyService.listCompany(companyId);
        return ResponseEntity.status(HttpStatus.OK).body(CompanyResponseDTO.convertToDto(company));
    }

    @ApiOperation("Este método é usado para deletar um estabelecimento a partir de seu id.")
    @DeleteMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> deleteCompany(@PathVariable String companyId) {

        CompanyModel company = this.companyService.deleteCompany(companyId);
        if (company == null) {
            return ResponseEntity.status(HttpStatus.OK).body(CompanyResponseDTO.convertToDto(new CompanyModel()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(CompanyResponseDTO.convertToDto(company));

    }

}

package com.stefanini.StefaniniFood.service.impl;


import com.stefanini.StefaniniFood.dto.company.CompanyRequestDTO;
import com.stefanini.StefaniniFood.dto.company.UpdateCompanyDataDTO;
import com.stefanini.StefaniniFood.exceptions.CompanyAlreadyExistsException;
import com.stefanini.StefaniniFood.exceptions.CompanyNotFoundException;
import com.stefanini.StefaniniFood.model.CompanyModel;
import com.stefanini.StefaniniFood.repository.CompanyRepository;
import com.stefanini.StefaniniFood.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, PasswordEncoder passwordEncoder ){
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public CompanyModel saveCompany(CompanyRequestDTO companyRequestDTO) {
        Optional<CompanyModel> companyAlreadyExists = this.companyRepository.findByEmail(companyRequestDTO.getEmail());
        if(companyAlreadyExists.isPresent()){
            throw new CompanyAlreadyExistsException("Estabelecimento já existe!");
        }

        var companyModel = new CompanyModel();
        companyModel.setName(companyRequestDTO.getName());
        companyModel.setEmail(companyRequestDTO.getEmail());
        companyModel.setPassword(passwordEncoder.encode(companyRequestDTO.getPassword()));
        companyModel.setCellphone(companyRequestDTO.getCellphone());
        companyModel.setCnpj(companyRequestDTO.getCnpj());
        companyModel.setLogin(companyRequestDTO.getLogin());
        companyModel.setPostalCode(companyRequestDTO.getPostalCode());
        companyModel.setPostalCode(companyRequestDTO.getPostalCode());
        companyModel.setState(companyRequestDTO.getState());
        companyModel.setCity(companyRequestDTO.getCity());
        companyModel.setStreet(companyRequestDTO.getStreet());
        companyModel.setDistrict(companyRequestDTO.getDistrict());
        companyModel.setNumber(companyRequestDTO.getNumber());
        companyModel.setReferencePoint(companyRequestDTO.getReferencePoint());

        return this.companyRepository.save(companyModel);

    }

    @Override
    public CompanyModel listCompany(String companyId) {
        Optional<CompanyModel> company = this.companyRepository.findById(UUID.fromString(companyId));
        if(company.isEmpty()){
            throw new CompanyNotFoundException("Estabelecimento não encontrado!");
        }
        return company.get();
    }

    @Override
    public List<CompanyModel> listAllCompany() {

        return this.companyRepository.findAll();
    }

    @Override
    public CompanyModel updateCompany(String companyId, UpdateCompanyDataDTO updateCompanyDataDTO) {
       var companyAlreadyExists = this.companyRepository.findById(UUID.fromString(companyId));

       if(companyId.isEmpty()){
           throw new CompanyNotFoundException("Estabelecimento não encontrado!");
       }
       var company = companyAlreadyExists.get();

       company.setName(updateCompanyDataDTO.getName());
       company.setCellphone(updateCompanyDataDTO.getCellphone());
       company.setEmail(updateCompanyDataDTO.getEmail());

       this.companyRepository.save(company);
       return company;
    }

    @Override
    public CompanyModel deleteCompany(String companyId) {
        var companyAlreadyExists = this.companyRepository.findById(UUID.fromString(companyId));
        if(companyAlreadyExists.isEmpty()){
            throw new CompanyNotFoundException("Estabelecimento não encontrado!");
        }

        this.companyRepository.deleteById(companyAlreadyExists.get().getId());
        return null;
    }
}

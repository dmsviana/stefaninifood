package com.stefanini.StefaniniFood.service;

import com.stefanini.StefaniniFood.dto.company.CompanyRequestDTO;
import com.stefanini.StefaniniFood.dto.company.UpdateCompanyDataDTO;
import com.stefanini.StefaniniFood.model.CompanyModel;

import java.util.List;

public interface CompanyService {

    CompanyModel saveCompany(CompanyRequestDTO company);

    CompanyModel listCompany(String companyId);

    List<CompanyModel> listAllCompany();

    CompanyModel updateCompany(String companyId, UpdateCompanyDataDTO updateCompanyDataDTO);

    CompanyModel deleteCompany(String companyId);

}

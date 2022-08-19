package com.stefanini.StefaniniFood.dto.company;



import com.stefanini.StefaniniFood.model.CompanyModel;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public class CompanyResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private String cnpj;
    private String cellphone;
    private String login;
    private String postalCode;
    private String state;
    private String city;
    private String street;
    private String district;
    private String number;
    private String referencePoint;


    public static CompanyResponseDTO convertToDto(CompanyModel company){
        var companyResponseDTO = new CompanyResponseDTO();

        companyResponseDTO.setId(company.getId());
        companyResponseDTO.setName(company.getName());
        companyResponseDTO.setCnpj(company.getCnpj());
        companyResponseDTO.setEmail(company.getEmail());
        companyResponseDTO.setLogin(company.getLogin());
        companyResponseDTO.setCellphone(company.getCellphone());
        companyResponseDTO.setPostalCode(company.getPostalCode());
        companyResponseDTO.setState(company.getState());
        companyResponseDTO.setCity(company.getCity());
        companyResponseDTO.setStreet(company.getStreet());
        companyResponseDTO.setDistrict(company.getDistrict());
        companyResponseDTO.setNumber(company.getNumber());
        companyResponseDTO.setReferencePoint(company.getReferencePoint());


        return companyResponseDTO;
    }

}

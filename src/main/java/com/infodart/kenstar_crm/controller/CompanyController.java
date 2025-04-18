package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.service.AuthService;
import com.infodart.kenstar_crm.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/companyManage")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

//	@PostMapping(value = "/addCompany")
//	public ResponseEntity<ResponseDto<CompanyDto>> addCompany(@RequestBody CompanyDto companyDto) {
//		CompanyDto comDto = companyService.addCompany(companyDto);
//		return ResponseEntity.ok(ResponseDto.success("200", "Company added", comDto));
//	}
//
//	@PostMapping(value = "/getCompany")
//	public ResponseEntity<ResponseDto<CompanyDto>> getCompany(@RequestBody CompanyDto companyDto) {
//		CompanyDto comDto = companyService.getCompany(companyDto);
//		return ResponseEntity.ok(ResponseDto.success("200", "Company Data found", comDto));
//	}
//
//	@GetMapping(value = "/getAllCompany")
//	public ResponseEntity<ResponseDto<List<CompanyDto>>> getAllCompany() {
//		List<CompanyDto> companyList = companyService.getAllCompany();
//		return ResponseEntity.ok(ResponseDto.success("200", "Company list found", companyList));
//	}
	
	
	
	
	// Add a new company
	@PostMapping(value = "/addCompany")
    @Operation(summary = "Add Company", description = "Add company details into the database")
    public ResponseEntity<ResponseDto<CompanyDto>> addCompany(@RequestBody CompanyDto companyDto) {
        try {
            CompanyDto createdCompany = companyService.addCompany(companyDto);
            ResponseDto<CompanyDto> responseDto = ResponseDto.success("200", "Company created successfully", createdCompany);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            ResponseDto<CompanyDto> responseDto = ResponseDto.error("500", "Failed to create company", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // Get company by ID
    @GetMapping("/{companyId}")
    @Operation(summary = "Get Company", description = "Get company details into the database")
    public ResponseEntity<ResponseDto<CompanyDto>> getCompanyById(@PathVariable Integer companyId) {
        try {
            CompanyDto companyDto = companyService.getCompanyById(companyId);
            ResponseDto<CompanyDto> responseDto = ResponseDto.success("200", "Company found", companyDto);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            ResponseDto<CompanyDto> responseDto = ResponseDto.error("404", "Company not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    // Get all companies
    @GetMapping
    @Operation(summary = "Get all Company", description = "Get all company details into the database")
    public ResponseEntity<ResponseDto<List<CompanyDto>>> getAllCompanies() {
        try {
            List<CompanyDto> companyDtoList = companyService.getAllCompanies();
            if (companyDtoList.isEmpty()) {
                ResponseDto<List<CompanyDto>> responseDto = ResponseDto.error("204", "No companies found", null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
            }
            ResponseDto<List<CompanyDto>> responseDto = ResponseDto.success("200", "Companies retrieved successfully", companyDtoList);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            ResponseDto<List<CompanyDto>> responseDto = ResponseDto.error("500", "An error occurred while retrieving companies", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // Update company
    @PutMapping("/{companyId}")
    @Operation(summary = "Update Company", description = "Update company details into the database")
    public ResponseEntity<ResponseDto<CompanyDto>> updateCompany(@PathVariable Integer companyId, @RequestBody CompanyDto companyDto) {
        try {
            CompanyDto updatedCompany = companyService.updateCompany(companyId, companyDto);
            ResponseDto<CompanyDto> responseDto = ResponseDto.success("200", "Company updated successfully", updatedCompany);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            ResponseDto<CompanyDto> responseDto = ResponseDto.error("404", "Company not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    // Delete company
    @DeleteMapping("/{companyId}")
    @Operation(summary = "Delete Company", description = "Delete company details into the database")
    public ResponseEntity<ResponseDto<Void>> deleteCompany(@PathVariable Integer companyId) {
        try {
            companyService.deleteCompany(companyId);
            ResponseDto<Void> responseDto = ResponseDto.success("200", "Company deleted successfully", null);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            ResponseDto<Void> responseDto = ResponseDto.error("404", "Company not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }
}

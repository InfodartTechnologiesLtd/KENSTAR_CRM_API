package com.infodart.kenstar_crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.LeaveBalanceDto;
import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.service.LeaveBalanceService;

@RestController
@RequestMapping("/leave-balance")
public class LeaveBalanceController {

	@Autowired
    private   LeaveBalanceService leaveBalanceService;

    @PostMapping("/initialize/{userId}")
    public ResponseEntity<ResponseDto<LeaveBalanceDto>>initialize(@PathVariable Long userId) {
    	LeaveBalanceDto leaveBalanceDto = leaveBalanceService.createOrInitializeBalance(userId);
		ResponseDto<LeaveBalanceDto> responseDto = ResponseDto.success("200", "Leave added successfully!", leaveBalanceDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDto<LeaveBalanceDto>> getByUser(@PathVariable Long userId) {
    	LeaveBalanceDto leaveBalanceDto = leaveBalanceService.getBalanceByUserId(userId);
		ResponseDto<LeaveBalanceDto> responseDto = ResponseDto.success("200", "Leave fetch successfully!", leaveBalanceDto);

        return ResponseEntity.ok(responseDto);
    }
}

package com.infodart.kenstar_crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.entity.LeaveBalance;
import com.infodart.kenstar_crm.service.LeaveBalanceService;

@RestController
@RequestMapping("/leave-balance")
public class LeaveBalanceController {

	@Autowired
    private   LeaveBalanceService leaveBalanceService;

    @PostMapping("/initialize/{userId}")
    public ResponseEntity<LeaveBalance> initialize(@PathVariable Long userId) {
        LeaveBalance balance = leaveBalanceService.createOrInitializeBalance(userId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<LeaveBalance> getByUser(@PathVariable Long userId) {
        LeaveBalance balance = leaveBalanceService.getBalanceByUserId(userId);
        return ResponseEntity.ok(balance);
    }
}

package com.flashstack.accounts.controller;

import com.flashstack.accounts.dto.CustomerDetailsDto;
import com.flashstack.accounts.service.ICustomerService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") @RequestParam String mobileNumber) {
        return new ResponseEntity<>(customerService.fetchCustomerDetails(mobileNumber), HttpStatus.OK);
    }
}

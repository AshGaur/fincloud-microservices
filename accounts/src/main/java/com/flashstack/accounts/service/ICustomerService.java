package com.flashstack.accounts.service;

import com.flashstack.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}

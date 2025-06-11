package com.flashstack.accounts.service.impl;

import com.flashstack.accounts.dto.AccountsDto;
import com.flashstack.accounts.dto.CardsDto;
import com.flashstack.accounts.dto.CustomerDetailsDto;
import com.flashstack.accounts.dto.LoansDto;
import com.flashstack.accounts.entity.Accounts;
import com.flashstack.accounts.entity.Customer;
import com.flashstack.accounts.exception.ResourceNotFoundException;
import com.flashstack.accounts.mapper.AccountsMapper;
import com.flashstack.accounts.mapper.CustomerMapper;
import com.flashstack.accounts.repository.AccountsRepository;
import com.flashstack.accounts.repository.CustomerRepository;
import com.flashstack.accounts.service.ICustomerService;
import com.flashstack.accounts.service.client.CardsFeignClient;
import com.flashstack.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Customer not found with given mobile number"));
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()-> new ResourceNotFoundException("Account not found with given account number"));
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if(loansDtoResponseEntity!=null){
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if(cardsDtoResponseEntity!=null) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }
        return customerDetailsDto;
    }
}

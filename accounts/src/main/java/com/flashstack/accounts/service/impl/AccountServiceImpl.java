package com.flashstack.accounts.service.impl;

import com.flashstack.accounts.constants.AccountsConstants;
import com.flashstack.accounts.dto.AccountsDto;
import com.flashstack.accounts.dto.CustomerDto;
import com.flashstack.accounts.entity.Accounts;
import com.flashstack.accounts.entity.Customer;
import com.flashstack.accounts.exception.CustomerAlreadyExistsException;
import com.flashstack.accounts.exception.ResourceNotFoundException;
import com.flashstack.accounts.mapper.AccountsMapper;
import com.flashstack.accounts.mapper.CustomerMapper;
import com.flashstack.accounts.repository.AccountsRepository;
import com.flashstack.accounts.repository.CustomerRepository;
import com.flashstack.accounts.service.IAccountsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number" + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    public CustomerDto fetchAccount(String mobileNumber) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(mobileNumber);
        Customer customer = customerOptional.orElseThrow(()-> new ResourceNotFoundException("Customer not found with given mobile number"));
        Optional<Accounts> accountOptional = accountsRepository.findByCustomerId(customer.getCustomerId());
        Accounts account = accountOptional.orElseThrow(()-> new ResourceNotFoundException("Account not found with given account number"));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return customerDto;
    }

    @Transactional
    @Override
    public void updateAccount(CustomerDto customerDto) {
        Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(()-> new ResourceNotFoundException("Customer not found with given mobile number"));
        customerRepository.save(CustomerMapper.mapToCustomer(customerDto, customer));

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()-> new ResourceNotFoundException("Account not found with given customer id"));
        accountsRepository.save(AccountsMapper.mapToAccounts(customerDto.getAccountsDto(), account));
    }

    @Transactional
    @Override
    public void deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Customer not found with given mobile number"));
        customerRepository.deleteByCustomerId(customer.getCustomerId());

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()-> new ResourceNotFoundException("Account not found with given customer id"));
        accountsRepository.deleteByAccountNumber(account.getAccountNumber());
    }
}

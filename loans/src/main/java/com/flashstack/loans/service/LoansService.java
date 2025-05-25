package com.flashstack.loans.service;

import com.flashstack.loans.constants.LoansConstants;
import com.flashstack.loans.dto.LoansDto;
import com.flashstack.loans.entity.Loans;
import com.flashstack.loans.exception.LoanAlreadyExistsException;
import com.flashstack.loans.exception.ResourceNotFoundException;
import com.flashstack.loans.mapper.LoansMapper;
import com.flashstack.loans.repository.LoansRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@AllArgsConstructor
@Service
public class LoansService implements ILoansService {
    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        if(loansRepository.existsByMobileNumber(mobileNumber)) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Loan not found with given mobile number"));
        return LoansMapper.mapToLoansDto(loan, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(()-> new ResourceNotFoundException("Loan not found with given mobile number"));
        loansRepository.save(LoansMapper.mapToLoans(loansDto, loans));
        return true;
    }

    @Transactional
    @Override
    public boolean deleteLoan(String mobileNumber) {
        if(!loansRepository.existsByMobileNumber(mobileNumber)) {
            throw new ResourceNotFoundException("Loan not found with given mobile number");
        }
        loansRepository.deleteByMobileNumber(mobileNumber);
        return true;
    }
}

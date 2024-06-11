package com.example.accapplication.service;

import com.example.accapplication.Repository.AccountRepo;
import com.example.accapplication.Utils.AccountUtils;
import com.example.accapplication.dto.AccountDto;
import com.example.accapplication.model.Accounts;
import com.example.accapplication.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepo;
    private final ModelMapper modelMapper;


    public AccountResponse addAccount(AccountDto request){
        Accounts accounts = new Accounts();
        accounts.setFullName(request.getFirstName() + " " + request.getLastName());
        accounts.setGender(request.getGender());
        accounts.setPhone(request.getPhone());
        accounts.setAccountNumber(AccountUtils.generateAccountNumber());
        accounts.setBalanceBefore(00.0);
        accounts.setBalanceAfter(00.0);
        accounts.setWithdrawAmount(00.0);
        accounts.setDateCreated(new Date());
        accountRepo.save(accounts);
        return modelMapper.map(accounts, AccountResponse.class);

    }


    public AccountResponse deposit (String phone, Double amount){
        Optional<Accounts> optionalAccounts = accountRepo.findByPhone(phone);
        if(optionalAccounts.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }else{
            Accounts accounts = optionalAccounts.get();
            accounts.setBalanceAfter(accounts.getBalanceBefore() + amount);
            accountRepo.save(accounts);
            return modelMapper.map(accounts, AccountResponse.class);
        }
    }


    public AccountResponse redeposit (String phone, Double amount){
        Optional<Accounts> optionalAccounts = accountRepo.findByPhone(phone);
        if(optionalAccounts.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }else{
            Accounts accounts = optionalAccounts.get();
            accounts.setBalanceBefore(accounts.getBalanceAfter());
            accounts.setBalanceAfter(accounts.getBalanceBefore() + amount);
            accountRepo.save(accounts);
            return  modelMapper.map(accounts, AccountResponse.class);
        }
    }

    public String withdraw (String phone, Double amount){
        Optional<Accounts> optionalAccounts = accountRepo.findByPhone(phone);
        if(optionalAccounts.isEmpty()){
            throw  new RuntimeException("Account Not Found");
        }else {
            Accounts accounts = optionalAccounts.get();
            accounts.setWithdrawAmount(amount);
            accounts.setBalanceAfter(accounts.getBalanceAfter() - amount);
            accountRepo.save(accounts);
            return "Succesfully withdrawed";
        }
    }


    public double checkBalance(String phone){
        Optional<Accounts> optionalAccounts = accountRepo.findByPhone(phone);
        if(optionalAccounts.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }else{
            Accounts accounts = optionalAccounts.get();
            double balance = accounts.getBalanceAfter();
            return balance;
        }
    }



    public String deleteUser(Long id){
        Optional<Accounts> optionalAccounts = accountRepo.findById(id);
        if(optionalAccounts.isEmpty()){
            throw new  RuntimeException("Account Not Found");
        }else{
            Accounts accounts = optionalAccounts.get();
            accountRepo.delete(accounts);
            return "Account Successfully Deleted";
        }
    }

}

package com.example.accapplication.controller;

import com.example.accapplication.dto.AccountDto;
import com.example.accapplication.response.AccountResponse;
import com.example.accapplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banking/")
@RequiredArgsConstructor
public class AccountController {
    public final AccountService accountService;

    @GetMapping("/")
    public String helloAdmineController(){
        return "Banking level access";
    }

    @PostMapping("addAccount")
    public AccountResponse addAccount (@RequestBody AccountDto request){
        return accountService.addAccount(request);
    }

    @PutMapping("deposit")
    public AccountResponse deposit (@RequestParam("phone") String phone, @RequestParam("amount") Double amount){
        return accountService.deposit(phone, amount);
    }

    @PutMapping("redeposit")
    public AccountResponse redeposit (@RequestParam("phone") String phone, @RequestParam("amount") Double amount){
        return accountService.redeposit(phone, amount);
    }

    @PutMapping("withdraw")
    public String withdraw (@RequestParam("phone") String phone, @RequestParam("amount") Double amount){
        return accountService.withdraw(phone, amount);
    }


    @GetMapping("checkBalance")
    public double checkBalance (@RequestParam("phone") String phone){
        return accountService.checkBalance(phone);
    }



    @DeleteMapping("deleteUser")
    public String deleteUser(@RequestParam("id") Long id){
        return accountService.deleteUser(id);
    }
}

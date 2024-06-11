package com.example.accapplication.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AccountResponse {
    private String fullName;
    private String gender;
    private String phone;
    private int accountNumber;
    private double balanceBefore;
    private double balanceAfter;
    private double withdrawAmount;
    private Date dateCreated;

}

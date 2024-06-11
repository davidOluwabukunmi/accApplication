package com.example.accapplication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
}

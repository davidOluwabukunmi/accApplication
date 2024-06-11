package com.example.accapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Basic
    @Column(name = "gender", nullable = false)
    private String gender;

    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;

    @Basic
    @Column(name = "account_number", nullable = false)
    private int accountNumber;

    @Basic
    @Column(name = "balance_before", nullable = false)
    private double balanceBefore;

    @Basic
    @Column(name = "balance_after", nullable = false)
    private double balanceAfter;

    @Basic
    @Column(name = "withdraw_amount", nullable = false)
    private double withdrawAmount;

    @Basic
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;
}

